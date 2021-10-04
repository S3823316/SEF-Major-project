package service;

import interfaces.AdminService;
import model.Account.Account;
import model.Account.User;
import model.Shows.Movie;
import model.Shows.Review;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Map;

// class for running actions as Admin user
// placeholders only as not implemented

public class AdminServiceImpl extends AccountServiceImpl implements AdminService {
    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM account WHERE userid = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public void addUser(Account user) {

    }


    @Override
    public void editUser(Account user) {

    }

    @Override
    public boolean upgradeUser(int id) {
        String sql = "UPDATE account SET accounttype = 'admin' WHERE userid = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean downgradeUser(int id) {
        String sql = "UPDATE account SET accounttype = 'user' WHERE userid = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public List<Account> getUnapprovedUsers() {
        String sql = "SELECT * FROM account WHERE approved = false";
        List<Account> results = template.query(sql, new BeanPropertyRowMapper<>(Account.class));
        return results;
    }

    @Override
    public List<Account> getApprovedUsers() {
        String sql = "SELECT * FROM account WHERE approved = true";
        List<Account> results = template.query(sql, new BeanPropertyRowMapper<>(Account.class));
        return results;
    }

    @Override
    public Account getUsers(int userid) {
        String sql = "SELECT * FROM account WHERE userid = ?";
        //Map<String, Object> map = template.queryForMap(sql,userid);
        Account account = template.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), userid);
        //template.queryForObject(sql,)
        return account;
    }

    @Override
    public boolean approveUser(int id) {
        String sql = "UPDATE account SET approved = true WHERE userid = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean disableUser(int id) {
        String sql = "UPDATE account SET approved = false WHERE userid = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean approveReview(int reviewid) {
        String sql = "UPDATE review SET r_approved = true WHERE reviewid = " + reviewid;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean disableReview(int reviewid) {
        String sql = "UPDATE review SET r_approved = false WHERE reviewid = " + reviewid;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean deleteReview(int reviewid) {
        String sql = "DELETE FROM review WHERE reviewid = " + reviewid;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public List<Review> getUnapprovedReview() {
        String sql = "SELECT * FROM review WHERE r_approved = false";
        List<Review> results = template.query(sql, new BeanPropertyRowMapper<>(Review.class));
        return results;
    }

    @Override
    public List<Review> getApprovedReview() {
        String sql = "SELECT * FROM review WHERE r_approved = true";
        List<Review> results = template.query(sql, new BeanPropertyRowMapper<>(Review.class));
        return results;
    }

    @Override
    public Movie getShow(int showid) {
        String sql = "SELECT * FROM show WHERE id = ?";
        Movie movie = template.queryForObject(sql, new BeanPropertyRowMapper<Movie>(Movie.class), showid);
        return movie;
    }

    @Override
    public boolean approveShow(int id) {
        String sql = "UPDATE shows SET approved = true WHERE id = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean disableShow(int id) {
        String sql = "UPDATE shows SET approved = false WHERE id = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public boolean deleteShow(int id) {
        String sql = "DELETE FROM shows WHERE id = " + id;
        int count = template.update(sql);
        return count > 0;
    }

}
