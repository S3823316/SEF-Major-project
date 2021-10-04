import controller.AdminController;
import controller.IndexController;
import controller.LoginController;
import controller.ShowController;
import controller.paths.Web;
import controller.utils.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class Application {

    // primary class for running Javalin application
    public static void main(String[] args){
        // create javalin app object
        // add reference for static file location
        // add reference for app routes, as included below
        // start service on localhost:7000
        Javalin app = Javalin.create(config -> {
                    config.addStaticFiles("/public");
                    config.registerPlugin(new RouteOverviewPlugin("/routes"));
                }
        ).start(7000);

        // set up app routes
        // get methods call respective classes to handle actions upon page visit
        // post methods call respective classes to handle actions upon form submit
        // page locations reference Web class
        app.routes(() -> {
            app.get(Web.INDEX, IndexController.serveIndexPage);
            app.get(Web.LOGIN, LoginController.serveLoginPage);
            app.get(Web.LOGOUT, LoginController.handleLogoutPost);
            app.get(Web.REGISTER, LoginController.serveRegisterPage);
            app.get(Web.REGISTER_PCO_CRITIC, LoginController.serveRegisterPcoCriticPage);
            app.get(Web.SHOWS_ALL, ShowController.fetchAllShows);
            app.get(Web.SHOWS_ONE, ShowController.fetchOneShow);
            app.get(Web.SHOW_CREATE, ShowController.serveCreateShowPage);
            app.get(Web.SHOW_EDIT, ShowController.serveEditShowPage);
            app.get(Web.ADMIN_REGISTER_USER, AdminController.serveRegisterAdminPage);
            app.get(Web.ADMIN_APPROVE_USERS, AdminController.serveApproveUsersPage);
            app.get(Web.ADMIN_APPROVE_USER_ID, AdminController.approveUser);
            app.get(Web.ADMIN_DISABLE_USER, AdminController.disableUser);
            app.get(Web.ADMIN_DELETE_USER, AdminController.deleteUser);
            app.get(Web.ADMIN_UPGRADE_USER, AdminController.upgradeUser);
            app.get(Web.ADMIN_DOWNGRADE_USER, AdminController.downgradeUser);

            app.get(Web.ADMIN_APPROVE_SHOWS, AdminController.serveApproveShowPage);
            app.get(Web.ADMIN_APPROVE_SHOW_ID, AdminController.approveShow);
            app.get(Web.ADMIN_DISABLE_SHOW, AdminController.disableShow);
            app.get(Web.ADMIN_DELETE_SHOW, AdminController.deleteShow);

            app.post(Web.LOGIN, LoginController.handleLoginPost);
            app.post(Web.REGISTER, LoginController.handleRegisterPost);
            app.post(Web.REGISTER_PCO_CRITIC, LoginController.handleRegisterPost);
            app.post(Web.ADMIN_REGISTER_USER, LoginController.handleRegisterPost);
            app.post(Web.SHOWS_ONE, ShowController.postReview);
            app.post(Web.SHOW_CREATE, ShowController.createShow);
            app.post(Web.SHOW_EDIT, ShowController.editShow);

            app.get(Web.ADMIN_APPROVE_REVIEWS,AdminController.serveApproveReviewPage);
            app.get(Web.ADMIN_APPROVE_REVIEW_ID,AdminController.approveReview);
            app.get(Web.ADMIN_DISABLE_REVIEW, AdminController.disableReview);
            app.get(Web.ADMIN_DELETE_REVIEW, AdminController.deleteReview);
        });

        // call ViewUtil to generate 404 error page if unknown page visited
        app.error(404, ViewUtil.notFound);
    }
}
