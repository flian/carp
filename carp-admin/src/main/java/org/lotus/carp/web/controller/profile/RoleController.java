package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.RoleConverter;
import org.lotus.carp.profile.service.impl.RoleServiceImpl;
import org.lotus.carp.profile.vo.RoleResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *  系统角色管理
 *
 * @author: Foy Lian
 * Date: 11/9/2017
 * Time: 5:12 PM
 */
@Controller
@RequestMapping("/roles")
public class RoleController extends AdminBaseController {
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private RoleConverter roleConverter;

    @GetMapping
    public String list() {
        return "/profile/roles";
    }

    @GetMapping(value = "/data")
    @ResponseBody
    public ResponseWrapper<Page<RoleResult>> queryRoles(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(roleService.search(q, page).map(roleConverter));
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseWrapper<List<RoleResult>> allRoles(){
        return response().execSuccess(roleConverter.map(roleService.all()));
    }
}
