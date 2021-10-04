package controller;

import controller.paths.Template;
import controller.paths.Web;
import controller.utils.RequestUtil;
import controller.utils.ViewUtil;
import interfaces.AdminService;
import io.javalin.http.Handler;
import model.Account.Account;
import service.AdminServiceImpl;

import java.util.Map;

public class AdminController {

    public static Handler serveApproveUsersPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            ctx.sessionAttribute("previousPage", ctx.path());
            model.put("unapprovedaccounts", ApplicationController.getUnapprovedUsers(ctx));
            model.put("approvedaccounts", ApplicationController.getApprovedUsers(ctx));
            ctx.render(Template.ADMIN_APPROVE_USER, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler approveUser = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.approveUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler disableUser = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.disableUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler deleteUser = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.deleteUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler upgradeUser = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.upgradeUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler downgradeUser = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.downgradeUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    //
    public static Handler serveRegisterAdminPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            ctx.sessionAttribute("previousPage", ctx.path());
            ctx.render(Template.ADMIN_REGISTER_USER, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler serveApproveShowPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            ctx.sessionAttribute("previousPage", ctx.path());
            model.put("unapprovedshows", ShowController.getUnapprovedShows(ctx));
            model.put("approvedshows", ShowController.getApprovedShows(ctx));
            ctx.render(Template.ADMIN_APPROVE_SHOW, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler approveShow = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.approveShow(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_SHOWS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler disableShow = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.disableShow(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_SHOWS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler deleteShow = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.deleteShow(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_USERS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };


    public static Handler approveReview = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){

            AdminService adminService = new AdminServiceImpl();
            System.out.println(Integer.parseInt(ctx.pathParam("id")));
            adminService.approveReview(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_REVIEWS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler disableReview = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.disableShow(Integer.parseInt(ctx.pathParam("id")));
            System.out.println(adminService.disableReview(Integer.parseInt(ctx.pathParam("id"))));
            ctx.redirect(Web.ADMIN_APPROVE_REVIEWS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };
    public static Handler deleteReview = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            AdminService adminService = new AdminServiceImpl();
            adminService.deleteReview(Integer.parseInt(ctx.pathParam("id")));
            ctx.redirect(Web.ADMIN_APPROVE_REVIEWS);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler serveApproveReviewPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && accountType.equals("admin")){
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            ctx.sessionAttribute("previousPage", ctx.path());
            ApplicationController.getUnapprovedReviews(ctx);
            model.put("unapprovedreviews", ApplicationController.getUnapprovedReviews(ctx));
            //System.out.println(ApplicationController.getUnapprovedReviews(ctx));
            model.put("approvedreviews", ApplicationController.getApprovedReviews(ctx));
            //System.out.println(ApplicationController.getApprovedReviews(ctx));
            ctx.render(Template.ADMIN_APPROVE_REVIEW, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

}
