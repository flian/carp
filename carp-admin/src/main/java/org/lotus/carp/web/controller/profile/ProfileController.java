package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.UserConverter;
import org.lotus.carp.profile.service.impl.UserServiceImpl;
import org.lotus.carp.profile.vo.UserCreateDto;
import org.lotus.carp.profile.vo.UserPasswordDto;
import org.lotus.carp.profile.vo.UserResult;
import org.lotus.carp.profile.vo.UserRoleUpdateDto;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用户管理
 *
 * @author : Foy Lian
 *         Date: 8/4/2017
 *         Time: 3:51 PM
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends AdminBaseController {
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String list() {
        return "profile/list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<UserResult> queryUsers(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(userService.search(q, page).map(userConverter));
    }

    @PutMapping("/roles")
    @ResponseBody
    public ResponseWrapper<UserResult> updateUserRoles(@Valid @RequestBody UserRoleUpdateDto userRoleUpdateDto) {
        return response().execSuccess(userConverter.convert(userService.updateUserRole(userRoleUpdateDto)));
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<UserResult> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return response().execSuccess(userConverter.convert(userService.createUser(userCreateDto)));
    }

    @PutMapping("{userName}/password")
    @ResponseBody
    public ResponseWrapper<UserResult> updatePasswrod(@PathVariable("userName") String userName,
                                                      @Valid @RequestBody UserPasswordDto userPasswordDto) {
        return response().execSuccess(userConverter.convert(userService.updatePassword(userName, userPasswordDto)));
    }
}
