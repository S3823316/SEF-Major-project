package controller;

import controller.paths.Template;
import controller.utils.ViewUtil;
import io.javalin.http.Handler;

import java.util.Map;

public class IndexController {
    // Generate index page based off of ViewUtil model
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", ApplicationController.viewRandomShow(ctx));
        ctx.sessionAttribute("previousPage", ctx.path());
        ctx.render(Template.INDEX, model);
    };
}
