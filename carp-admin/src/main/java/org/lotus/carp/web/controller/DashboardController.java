package org.lotus.carp.web.controller;

import org.lotus.carp.base.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/2/2017
 * Time: 3:00 PM
 */
@Controller
@RequestMapping("/admin")
public class DashboardController implements BaseController {

    @GetMapping(value = {"", "/index", "/home", "/dashboard"})
    public String index() {
        return "/dashboard";
    }
}
