package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.UserConvter;
import org.lotus.carp.profile.service.impl.UserServiceImpl;
import org.lotus.carp.profile.vo.UserResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/4/2017
 * Time: 3:51 PM
 */
@Controller
@RequestMapping("/profile")
public class ProfileManagementController extends AdminBaseController {
    @Autowired
    private UserConvter userConvter;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping
    public String list() {
        return "profile/list";
    }

    @RequestMapping("/data")
    @ResponseBody
    public ResponseWrapper<UserResult> queryUsers(String q, Pageable page) {
        return response().execSuccess(userConvter.toList(userService.search(q, page)));
    }
}
