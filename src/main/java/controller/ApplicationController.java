package controller;

import controller.utils.RequestUtil;
import interfaces.AccountService;
import interfaces.AdminService;
import interfaces.PCoService;
import io.javalin.http.Context;
import model.Account.Account;
import model.Shows.Review;
import model.Shows.TVShow;
import service.AccountServiceImpl;
import service.AdminServiceImpl;
import service.CriticServiceImpl;
import service.PCoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApplicationController {

    // pull show data from database using id parameter in /shows/:id
    // queries managed by AccountServiceImpl() class
    public static TVShow viewShow(Context context) {
        AccountService accountService = new AccountServiceImpl();
        List<TVShow> results = accountService.view("id", context.pathParam("id"));
        if(results.size() > 0) {
            return results.get(0);
        }
        else{
            return null;
        }
    }

    // pull show data for all shows in database
    // returns Iterable for use in Velocity templates
    public static Iterable<TVShow> viewAllShows(Context context){
        AccountService accountService = new AccountServiceImpl();
        List<TVShow> results = accountService.view("title", "%");
        return results;
    }

    // pull show data for one random show in database
    public static TVShow viewRandomShow(Context context){
        AccountService accountService = new AccountServiceImpl();
        TVShow results = accountService.view("title", "%' AND approved = true ORDER BY RAND() -- ").get(0);
        return results;
    }

    // pull name of specific production company
    public static String viewPCo(Context context) {
        AccountService accountService = new AccountServiceImpl();
        String results = accountService.viewPCo(Integer.parseInt(context.pathParam("id")));
        return results;
    }

    // pull name and id from all production companies
    public static Iterable<Map<String, Object>> viewAllPCo(Context context){
        AccountService accountService = new AccountServiceImpl();
        List<Map<String, Object>> results = accountService.viewAllPCo();
        return results;
    }

    // pull show data for all reviews for specific show in database
    // returns Iterable for use in Velocity templates on show page
    public static Iterable<Map<String, Object>> viewAllReviews(Context context) {
        AccountService accountService = new AccountServiceImpl();
        List<Map<String, Object>> results = accountService.viewReviews("showid", Integer.parseInt(context.pathParam("id")));
        return results;
    }

    // pass login attempt from form to AccountServiceImpl() method
    // all authentication and queries handled in AccountServiceImpl()
    // will return null if account not valid
    // Otherwise, returns account data for parsing
    public static Account login(Context context) {
        String accountType = context.formParam("accountType");
        String username = context.formParam("username");
        String password = context.formParam("password");
        AccountService accountService = new AccountServiceImpl();
        Account result = accountService.login(accountType, username, password);
        return result;
    }

    // pass signup attempt from form to AccountServiceImpl() method
    // all authentication and queries handled in AccountServiceImpl()
    // will return null if signup failed
    // Otherwise, returns account data for parsing
    // ID is never needed to be treated like an integer, thus is simply taken as String
    public static Account signup(Context context) {
        String pcoid = context.formParam("pcoid");
        String accountType = context.formParam("accountType");
        if(accountType == null){ accountType = "user";}
        String username = context.formParam("username");
        String password = context.formParam("password");
        String email = context.formParam("email");
        String country = context.formParam("country");
        String gender = context.formParam("gender");
        String firstName = context.formParam("firstname");
        String lastName = context.formParam("lastname");
        String organisation = context.formParam("organisation");
        String phone = context.formParam("phone");
        AccountService accountService = new AccountServiceImpl();
        Account result = accountService.signup(pcoid, accountType, username, password, email, country, gender, firstName, lastName, organisation, phone);
        return result;
    }

    // pass submitted review from form to managing method in AccountServiceImpl()
    // returns true if review successful
    public static boolean review(Context context){

        int showID = Integer.parseInt(context.pathParam(":id"));
        String reviewComment = context.formParam("reviewComment");
        int reviewRating = Integer.parseInt(context.formParam("reviewRating"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(date);
        int userID = Integer.parseInt(RequestUtil.getSessionCurrentUserID(context));
        String accountType = RequestUtil.getSessionCurrentUserType(context);
        boolean approved = false;
        if(accountType != null && (accountType.equals("admin") )){
            approved = true;
        }
        AccountService accountService = new AccountServiceImpl();
        boolean result = accountService.review(userID, showID, reviewRating, reviewComment, currentDate,approved);
        return result;
    }

    // pull all unapproved users in database
    public static List<Account> getUnapprovedUsers(Context ctx) {
        AdminService adminService = new AdminServiceImpl();
        List<Account> results = adminService.getUnapprovedUsers();
        return results;
    }

    // pull all approved users in database
    public static List<Account> getApprovedUsers(Context ctx){
        AdminService adminService = new AdminServiceImpl();
        List<Account> results = adminService.getApprovedUsers();
        return results;
    }


    // pull all unapproved review in database
    public static List<Map<String, Object>> getUnapprovedReviews(Context ctx) {
        //AdminService adminService = new AdminServiceImpl();
        //List<Review> results = adminService.getUnapprovedReview();
        AccountService accountService = new AccountServiceImpl();
        List<Map<String, Object>> results = accountService.viewReviews("r_approved", 0);
        //List<Map<String, Object>> results = accountService.viewReviews("showid", Integer.parseInt(context.pathParam("id")));
        System.out.println(results);
        return results;
    }

    // pull all approved review in database
    public static List<Map<String, Object>> getApprovedReviews(Context ctx){
//        AdminService adminService = new AdminServiceImpl();
//        List<Review> results = adminService.getApprovedReview();
        AccountService accountService = new AccountServiceImpl();
        List<Map<String, Object>> results = accountService.viewReviews("r_approved", 1);
        System.out.println(results);
        return results;
    }


    public static boolean createShow(Context ctx) {
        String pcoid = RequestUtil.getSessionCurrentUserPCo(ctx);
        String title = ctx.formParam("title");
        String description = ctx.formParam("description");
        String runtime = ctx.formParam("runtime");
        String releasedate = ctx.formParam("releasedate");
        String language = ctx.formParam("language");
        String country = ctx.formParam("country");
        String genre = ctx.formParam("genre");
        String image = ctx.formParam("image");
        PCoService pCoService = new PCoServiceImpl();
        boolean results = pCoService.createTVShow(pcoid, title, description, runtime, releasedate, language, country, genre, image);
        return results;
    }
}
