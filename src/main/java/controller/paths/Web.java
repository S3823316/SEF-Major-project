package controller.paths;

public class Web {
    // initialise web page locations
    public static final String INDEX = "/";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REGISTER = "/register";
    public static final String REGISTER_PCO_CRITIC = "/register-pco-critic";
    public static final String SHOWS_ALL = "/shows";
    public static final String SHOWS_ONE = "/shows/:id";
    public static final String SHOW_CREATE = "/create-show";
    public static final String SHOW_EDIT = "/edit-show/:id";
    public static final String ADMIN_REGISTER_USER = "/admin/register-user";
    public static final String ADMIN_APPROVE_USERS = "/admin/approve-user";
    public static final String ADMIN_APPROVE_USER_ID = "/admin/approve-user/:id";
    public static final String ADMIN_DISABLE_USER = "/admin/disable-user/:id";
    public static final String ADMIN_DELETE_USER = "/admin/delete-user/:id";
    public static final String ADMIN_UPGRADE_USER = "/admin/upgrade-user/:id";
    public static final String ADMIN_DOWNGRADE_USER = "/admin/downgrade-user/:id";
    public static final String ADMIN_APPROVE_SHOWS = "/admin/approve-show";
    public static final String ADMIN_APPROVE_SHOW_ID = "/admin/approve-show/:id";
    public static final String ADMIN_DISABLE_SHOW = "/admin/disable-show/:id";
    public static final String ADMIN_DELETE_SHOW = "/admin/delete-show/:id";

    public static final String ADMIN_APPROVE_REVIEWS = "/admin/approve-review";
    public static final String ADMIN_APPROVE_REVIEW_ID= "/admin/approve-review/:id";
    public static final String ADMIN_DISABLE_REVIEW = "/admin/disable-review/:id";
    public static final String ADMIN_DELETE_REVIEW = "/admin/delete-review/:id";

}
