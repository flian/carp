package org.lotus.carp.commerce.web.controller.customer;

import org.lotus.carp.base.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/30/2017
 * Time: 11:21 AM
 */

@Controller
@RequestMapping("/public/customer")
public class CustomerAdminController implements BaseController {
    @GetMapping
    public String index() {
        return buildPage("customer/index");
    }

    private String buildPage(String url) {
        return String.format("commerce/%s", url);
    }
}
