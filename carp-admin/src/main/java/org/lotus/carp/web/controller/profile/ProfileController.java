package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.UserConverter;
import org.lotus.carp.profile.service.impl.UserServiceImpl;
import org.lotus.carp.profile.vo.UserResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *  用户管理
 *
 * @author : Foy Lian
 *         Date: 8/4/2017
 *         Time: 3:51 PM
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends AdminBaseController {
    @Autowired
    private UserConverter userConvter;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String list() {
        return "profile/list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<UserResult> queryUsers(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(userService.search(q, page).map(userConvter));
    }
}
