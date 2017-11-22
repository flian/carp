package org.lotus.carp.web.controller.security;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.security.convter.RoleConverter;
import org.lotus.carp.security.service.impl.RoleServiceImpl;
import org.lotus.carp.security.vo.ResourceIdListResult;
import org.lotus.carp.security.vo.ResourceListResult;
import org.lotus.carp.security.vo.RoleCreateDto;
import org.lotus.carp.security.vo.RoleResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统角色管理
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
        return "/security/roles";
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<RoleResult> createRole(@Valid @RequestBody RoleCreateDto dto){
        return response().execSuccess(roleConverter.convert(roleService.createRole(dto)));
    }

    @GetMapping(value = "/data")
    @ResponseBody
    public ResponseWrapper<Page<RoleResult>> queryRoles(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(roleService.search(q, page).map(roleConverter));
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseWrapper<List<RoleResult>> allRoles() {
        return response().execSuccess(roleConverter.map(roleService.all()));
    }

    @GetMapping(value = "/resources")
    @ResponseBody
    public ResponseWrapper<ResourceListResult> allResources() {
        return response().execSuccess(roleService.allResources());
    }

    @GetMapping(value = "{roleId}/resources")
    @ResponseBody
    public ResponseWrapper<ResourceIdListResult> roleResources(@PathVariable("roleId") Long roleId) {
        return response().execSuccess(roleService.findRoleResourceIds(roleId));
    }

    @PutMapping("{roleId}/menus")
    @ResponseBody
    public ResponseWrapper<ResourceIdListResult> saveRoleMenu(
            @PathVariable("roleId") Long roleId,
            @RequestBody List<Integer> menuIds) {
        roleService.updateRoleMenu(roleId, menuIds);
        return response().execSuccess(roleService.findRoleResourceIds(roleId));
    }

    @PutMapping("{roleId}/actions")
    @ResponseBody
    public ResponseWrapper<ResourceIdListResult> saveRoleAction(
            @PathVariable("roleId") Long roleId,
            @RequestBody List<Integer> actionIds) {
        roleService.updateRoleAction(roleId, actionIds);
        return response().execSuccess(roleService.findRoleResourceIds(roleId));
    }

}
