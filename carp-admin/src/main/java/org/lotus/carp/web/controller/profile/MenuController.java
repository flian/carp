package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.MenuConvter;
import org.lotus.carp.profile.service.impl.MenuServiceImpl;
import org.lotus.carp.profile.vo.MenuResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  菜单授权管理
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:21 PM
 */
@Controller
@RequestMapping("/menus")
public class MenuController extends AdminBaseController {

    @Autowired
    private MenuConvter menuConvter;

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping
    public String list() {
        return "profile/menus";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<MenuResult> queryUsers(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(menuService.search(q, page).map(menuConvter));
    }
}
