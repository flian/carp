package org.lotus.carp.web.controller.profile;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.profile.convter.ActionConverter;
import org.lotus.carp.profile.service.impl.ActionServiceImpl;
import org.lotus.carp.profile.vo.ActionCreateDto;
import org.lotus.carp.profile.vo.ActionResult;
import org.lotus.carp.profile.vo.ActionUpdateDto;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能授权管理
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:21 PM
 */
@Controller
@RequestMapping("/actions")
public class ActionController extends AdminBaseController {
    @Autowired
    private ActionConverter actionConverter;
    @Autowired
    private ActionServiceImpl actionService;

    @GetMapping
    public String list() {
        return "profile/actions";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<List<ActionResult>> findAll() {
        return response().execSuccess(actionConverter.buildTree(actionService.findAll()));
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<ActionResult> add(@Valid @RequestBody ActionCreateDto createDto) {
        return response().execSuccess(actionConverter.convert(actionService.create(createDto)));
    }

    @PutMapping
    @ResponseBody
    public ResponseWrapper<ActionResult> update(@Valid @RequestBody ActionUpdateDto updateDto) {
        return response().execSuccess(actionConverter.convert(actionService.update(updateDto)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseWrapper<ActionResult> delete(@PathVariable Integer id) {
        return response().execSuccess(actionConverter.convert(actionService.delete(id)));
    }
}
