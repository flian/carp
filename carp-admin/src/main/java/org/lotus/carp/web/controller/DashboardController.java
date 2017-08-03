package org.lotus.carp.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/2/2017
 * Time: 3:00 PM
 */
@Controller
public class DashboardController extends AdminBaseController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @GetMapping(value = {"", "/index", "/home", "/dashboard"})
    public String index() {
        return "/dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "profile/login";
    }
    @Override
    public String getErrorPath() {
        return ADMIN_PATH + ERROR_PATH;
    }

    @RequestMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "没有权限！";
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public String error() {
        return "超人，出错啦！ 我们马上回来。";
    }
}
