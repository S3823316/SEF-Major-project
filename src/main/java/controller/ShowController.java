package controller;

import controller.paths.Template;
import controller.paths.Web;
import controller.utils.RequestUtil;
import controller.utils.ViewUtil;
import interfaces.AccountService;
import interfaces.AdminService;
import interfaces.PCoService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import model.Account.Account;
import model.Shows.TVShow;
import service.AccountServiceImpl;
import service.AdminServiceImpl;
import service.PCoServiceImpl;

import java.util.List;
import java.util.Map;

public class ShowController {
    // query all shows in database, via ApplicationController for handling
    // generate Velocity template using ViewUtil model
    public static Handler fetchAllShows = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("shows", ApplicationController.viewAllShows(ctx));
        model.put("featuredShow", ApplicationController.viewRandomShow(ctx));
        ctx.sessionAttribute("previousPage", ctx.path());
        ctx.render(Template.SHOWS_ALL, model);
    };

    // query single shows in database, via ApplicationController for handling
    // generate Velocity template using ViewUtil model
    // reviews fetched separately
    public static Handler fetchOneShow = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", ApplicationController.viewShow(ctx));
        model.put("reviews", ApplicationController.viewAllReviews(ctx));
        model.put("productionCompany", ApplicationController.viewPCo(ctx));
        ctx.sessionAttribute("previousPage", ctx.path());
        ctx.render(Template.SHOWS_ONE, model);
    };

    // handle posted review
    // if review successful, redirect to Index, otherwise display error message
    public static Handler postReview = ctx -> {
        if(ApplicationController.review(ctx)){
            ctx.redirect(ctx.sessionAttribute("previousPage"));
        }
        else{
            ctx.html("Error: Failed to submit review!");
        }
    };

    public static Handler serveCreateShowPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && (accountType.equals("admin") || accountType.equals("pco"))) {
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            model.put("show", ApplicationController.viewRandomShow(ctx));
            ctx.sessionAttribute("previousPage", ctx.path());
            ctx.render(Template.SHOW_CREATE, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler createShow = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && (accountType.equals("admin") || accountType.equals("pco"))) {
            if (ApplicationController.createShow(ctx)) {
                ctx.redirect(ctx.sessionAttribute("previousPage"));
            } else {
                ctx.html("Error: Failed to submit show!");
            }
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler serveEditShowPage = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && (accountType.equals("admin") || accountType.equals("pco"))) {
            Map<String, Object> model = ViewUtil.baseModel(ctx);
            model.put("show", ApplicationController.viewShow(ctx));
            ctx.sessionAttribute("previousPage", ctx.path());
            ctx.render(Template.SHOW_EDIT, model);
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };

    public static Handler editShow = ctx -> {
        String accountType = RequestUtil.getSessionCurrentUserType(ctx);
        if(accountType != null && (accountType.equals("admin") || accountType.equals("pco"))) {
            PCoService pCoService = new PCoServiceImpl();
            int id = Integer.parseInt(ctx.pathParam("id"));
            String title = ctx.formParam("title");
            String description = ctx.formParam("description");
            String runtime = ctx.formParam("runtime");
            String releasedate = ctx.formParam("releasedate");
            String language = ctx.formParam("language");
            String country = ctx.formParam("country");
            String genre = ctx.formParam("genre");
            String image = ctx.formParam("image");
            if (pCoService.editTVShow(id, title, description, runtime, releasedate, language, country, genre, image)) {
                ctx.redirect(ctx.sessionAttribute("previousPage"));
                ctx.redirect("/shows/" + id);
            } else {
                ctx.html("Error: Failed to submit show!");
            }
        }
        else{
            ctx.redirect(Web.LOGIN);
        }
    };


    // pull all unapproved shows in database
    public static List<TVShow> getUnapprovedShows(Context ctx) {
        AccountService accountService = new AccountServiceImpl();
        List<TVShow> results = accountService.getUnapprovedShows();
        return results;
    }

    // pull all approved shows in database
    public static List<TVShow> getApprovedShows(Context ctx){
        AccountService accountService = new AccountServiceImpl();
        List<TVShow> results = accountService.getApprovedShows();
        return results;
    }
}
