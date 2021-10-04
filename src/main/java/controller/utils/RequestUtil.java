package controller.utils;


import io.javalin.http.Context;

public class RequestUtil {

    // return attributes for both username and ID of current user
    // if user not logged in, attributes will return null
    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }

    public static String getSessionCurrentUserID(Context ctx) {
        return (String) ctx.sessionAttribute("currentUserID");
    }

    public static String getSessionCurrentUserType(Context ctx) {
        return (String) ctx.sessionAttribute("currentUserType");
    }

    public static String getSessionCurrentUserPCo(Context ctx) {
        return (String) ctx.sessionAttribute("currentUserPCo");
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = ctx.sessionAttribute("loggedOut");
        ctx.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }



}
