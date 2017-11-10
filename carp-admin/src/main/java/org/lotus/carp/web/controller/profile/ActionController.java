package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.ActionConvter;
import org.lotus.carp.profile.service.impl.ActionServiceImpl;
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
 *  功能授权管理
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:21 PM
 */
@Controller
@RequestMapping("/actions")
public class ActionController extends AdminBaseController {
    @Autowired
    private ActionConvter actionConvter;
    @Autowired
    private ActionServiceImpl actionService;

    @GetMapping
    public String list() {
        return "profile/actions";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<UserResult> queryUsers(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(actionService.search(q, page).map(actionConvter));
    }
}
