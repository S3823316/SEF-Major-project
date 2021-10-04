package service;

import controller.utils.ViewUtil;
import model.Account.Account;
import model.Shows.TVShow;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements interfaces.AccountService {

    // initialise Java Database Connectivity object
    JdbcTemplate template = new JdbcTemplate(ViewUtil.JDBCUtils.getDataSource());

    public AccountServiceImpl() {
    }

    //placeholder method for search function
    @Override
    public void search() {
    }

    // lowest level method in signup feature
    // send query to database using form data passed from ApplicationController class
    // if query is successful, call login method to return the newly created account
    // if failure in update query, return null
    @Override
    public Account signup(String pcoid, String accountType, String username, String password, String email, String country, String gender, String firstName, String lastName, String organisation, String phone) {
        boolean approved = false;
        if(accountType.equals("user")){
            approved = true;
        }
        if (!usernameExist(username)){
            String sql = "INSERT INTO account(pcoid, accounttype, username, password, email, country, gender, firstname, lastname, approved, organisation, phone) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            int count = template.update(sql, pcoid, accountType, username, password, email, country, gender, firstName, lastName, approved, organisation, phone);
            if(count > 0){
                Account newAccount = login(accountType, username, password);
                return newAccount;
            }
        }
        return null;
    }
    public boolean usernameExist(String username){
        boolean flag = false;
        String sql = "select * from account where username = ?";
        try {
            Map<String, Object> map = template.queryForMap(sql, username);
            if (map.size() > 0){
                flag = true;
                System.out.println("found one ");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // lowest level method in login feature
    // send query to database using form data passed from ApplicationController class
    // requires username and password to be filled out, and will refuse if not
    // query employs BeanPropertyRowMapper to map the query results to their matching Account class fields, using the class's provided setter methods
    // if login successful, will return Account object, otherwise null
    @Override
    public Account login(String accountType, String username, String password) {
        Account loggedIn = null;
        if (username == null || password == null) {
            return null;
        }
        String sql = "select * from account where username = \'" + username + "\' and password = \'" + password + "\' and accountType = '" + accountType + "'";
        List<Account> results = template.query(sql, new BeanPropertyRowMapper<>(Account.class));
        if(results.size() > 0){ loggedIn = results.get(0);}
        return loggedIn;
    }

    // placeholder method for logout lowest level logout function
    // currently not in use as session clearing is handled via Javalin
    @Override
    public void logout() {
    }

    // View shows
    // String column allows for searching by any column (id, title, etc)
    // Will return list of TVShow objects
    // method will iterate through each TVShow object in the list, and query the database for the average total of any reviews that match the show's id
    // if no reviews are found, the show will be set to 0
    @Override
    public List<TVShow> view(String column, String search) {
        String sql = "SELECT * FROM shows WHERE " + column + " LIKE '" + search + "'";
        List<TVShow> results = template.query(sql, new BeanPropertyRowMapper<>(TVShow.class));
        for(TVShow show: results){
            sql = "SELECT AVG(rating) as avgRating FROM review WHERE showid = " + show.getID();
            Map<String, Object> rating = template.queryForMap(sql);
            if(rating.get("avgRating") != null) {
                float test = Math.round(Float.parseFloat(rating.get("avgRating").toString()));
                show.setRating(Math.round(test));
            }
            else{
                show.setRating(0);
            }
        }
        return results;
    }

    public String viewPCo(int id){
        String sql = "select pconame from shows s INNER JOIN production_company pc ON s.pcoid = pc.pcoid WHERE id = " + id;
        List<Map<String, Object>> results = template.queryForList(sql);
        if(results.size() > 0) {
            return results.get(0).get("pconame").toString();
        }
        else{
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> viewAllPCo() {
        String sql = "select * from production_company";
        List<Map<String, Object>> results = template.queryForList(sql);
        return results;
    }

    @Override
    public List<TVShow> getUnapprovedShows() {
        String sql = "select * from shows WHERE approved = false";
        List<TVShow> results = template.query(sql, new BeanPropertyRowMapper<>(TVShow.class));
        return results;
    }

    @Override
    public List<TVShow> getApprovedShows() {
        String sql = "select * from shows WHERE approved = true";
        List<TVShow> results = template.query(sql, new BeanPropertyRowMapper<>(TVShow.class));
        return results;
    }

    // query database to pull all reviews for the given tv show, by show id
    // will return a list of maps for iteration
    public List<Map<String, Object>> viewReviews(String column, int id){
//        String sql = "select firstname, lastname, rating, review, reviewid, r_approved , date  " +
//                "from account a INNER JOIN review r ON a.userid = r.userid WHERE " + column + " = " + id ;
        String sql = "select firstname, lastname, rating, review, reviewid, r_approved , date  ,title " +
                "from review r, account a, shows s " +
                "WHERE a.userid = r.userid and s.id = r.showid and " + column + " = " + id ;

        List<Map<String, Object>> results = template.queryForList(sql);
        return results;
    }

    // lowest level method for posting reviews
    // queries the database to insert given information, passed from form via ApplicationController
    // if successful, return true
    @Override
    public boolean review(int userID, int showID, int rating, String review, String date,boolean approved){
        String sql = "INSERT INTO review(userID, showID, rating, review, date, r_approved) values(?,?,?,?,?,?)";
        int count = template.update(sql, userID, showID, rating, review, date, approved);
        return count > 0;
    }
}
