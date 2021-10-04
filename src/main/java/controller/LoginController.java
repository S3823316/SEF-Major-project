package controller;

import controller.paths.Template;
import controller.paths.Web;
import controller.utils.RequestUtil;
import controller.utils.ViewUtil;
import io.javalin.http.Handler;
import model.Account.Account;
import org.apache.commons.beanutils.BeanUtils;


import java.util.Map;

public class LoginController {

    // Generate login page based off of ViewUtil model
    // loggedOut and loginRedirect attribute settings are passed into page
    public static Handler serveLoginPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", RequestUtil.removeSessionAttrLoggedOut(ctx));
        model.put("show", ApplicationController.viewRandomShow(ctx));
        ctx.render(Template.LOGIN, model);
    };

    // pass login attempt to ApplicationController for handling
    // if login successful, redirect to index page with session set
    // account name and ID are saved to the session and stored in RequestUtil for further requests
    // if login failed, reload page
    public static Handler handleLoginPost = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel(ctx);
        Account account = ApplicationController.login(ctx);
        if (account == null || !account.getApproved() ){
            model.put("authenticationFailed", true);
            ctx.render(Template.LOGIN, model);
        } else {
            ctx.sessionAttribute("currentUser", account.getUsername());
            ctx.sessionAttribute("currentUserID", account.getUserID().toString());
            ctx.sessionAttribute("currentUserType", account.getAccountType());
            ctx.sessionAttribute("currentUserPCo", account.getPcoID());
            model.put("authenticationSucceeded", true);
            model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
            model.put("currentUserID", RequestUtil.getSessionCurrentUserID(ctx));
            model.put("currentUserType", RequestUtil.getSessionCurrentUserType(ctx));
            model.put("currentUserPCo", RequestUtil.getSessionCurrentUserPCo(ctx));
            if(ctx.sessionAttribute("previousPage") == null){
                ctx.redirect(Web.INDEX);
            }
            else {
                ctx.redirect(ctx.sessionAttribute("previousPage"));
            }
        }
    };

    // clear session upon logout and redirect user to index page
    public static Handler handleLogoutPost = ctx -> {
        ctx.sessionAttribute("currentUser", null);
        ctx.sessionAttribute("currentUserID", null);
        ctx.sessionAttribute("currentUserType", null);
        ctx.sessionAttribute("currentUserPCo", null);
        ctx.sessionAttribute("loggedOut", "true");
        ctx.redirect(Web.INDEX);
    };

    // Generate login page based off of ViewUtil model
    public static Handler serveRegisterPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        ctx.sessionAttribute("previousPage", ctx.path());
        model.put("show", ApplicationController.viewRandomShow(ctx));
        ctx.render(Template.REGISTER, model);
    };

    // Generate PCO/Critic register page based off of ViewUtil model
    public static Handler serveRegisterPcoCriticPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        ctx.sessionAttribute("previousPage", ctx.path());
        model.put("show", ApplicationController.viewRandomShow(ctx));
        model.put("production_companies", ApplicationController.viewAllPCo(ctx));
        ctx.render(Template.REGISTER_PCO_CRITIC, model);
    };

    // pass registration attempt to AccountServiceImpl() for handling
    // if successful, redirect to index page
    public static Handler handleRegisterPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("duplicatedUsername", false);
        model.put("differentPassword", false);
        String confirmPassword = ctx.formParam("confirmpassword");
        String password = ctx.formParam("password");
        Account account = ApplicationController.signup(ctx);
        if (account != null && password.equals(confirmPassword)) {
            ctx.redirect(Web.LOGIN);
        }else {
            if (account == null){
                model.put("duplicatedUsername", true);
            }
            if (!password.equals(confirmPassword) ){
                model.put("differentPassword", true);

            }
            ctx.render(Template.REGISTER, model);
        }
    };
}
