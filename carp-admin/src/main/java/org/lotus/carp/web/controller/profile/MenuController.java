package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.MenuConverter;
import org.lotus.carp.profile.service.impl.MenuServiceImpl;
import org.lotus.carp.profile.vo.MenuCreateDto;
import org.lotus.carp.profile.vo.MenuResult;
import org.lotus.carp.profile.vo.MenuUpdateDto;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单授权管理
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:21 PM
 */
@Controller
@RequestMapping("/menus")
public class MenuController extends AdminBaseController {

    @Autowired
    private MenuConverter menuConverter;

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping
    public String list() {
        return "profile/menus";
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<MenuResult> add(@Valid @RequestBody MenuCreateDto createDto) {
        return response().execSuccess(menuConverter.convert(menuService.create(createDto)));
    }

    @PutMapping
    @ResponseBody
    public ResponseWrapper<MenuResult> update(@Valid @RequestBody MenuUpdateDto updateDto) {
        return response().execSuccess(menuConverter.convert(menuService.update(updateDto)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseWrapper<MenuResult> delete(@PathVariable Integer id) {
        return response().execSuccess(menuConverter.convert(menuService.delete(id)));
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<List<MenuResult>> findAll() {
        return response().execSuccess(menuConverter.buildTree(menuService.findAll()));
    }
}
