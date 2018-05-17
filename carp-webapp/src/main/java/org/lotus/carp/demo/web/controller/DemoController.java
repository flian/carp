package org.lotus.carp.demo.web.controller;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.demo.dto.DemoHelloResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 5/17/2018
 * Time: 10:26 AM
 */

@RestController
@RequestMapping("/public/demo")
public class DemoController {
    @GetMapping
    public ResponseWrapper<List<DemoHelloResult>> list() {
        List<DemoHelloResult> result = new ArrayList<>();
        result.add(makeItem("carp", "hello,I'm carp"));
        result.add(makeItem("nobody", "hello,I'm nobody"));
        result.add(makeItem("bingo", "bingo"));
        return ResponseWrapper.create().execSuccess(result);
    }

    private DemoHelloResult makeItem(String who, String message) {
        DemoHelloResult item = new DemoHelloResult();
        item.setWho("carp");
        item.setMessge("hello, I'm carp!");
        item.setFullTime(new Date());
        item.setTime(new Date());
        return item;
    }
}
