package org.lotus.carp.api.customer.web;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.customer.service.CustomerService;
import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户rest API
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 10:41 AM
 */
@RestController
@RequestMapping("/api/users")
public class CustomerController implements BaseController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseWrapper<CustomerDetailResult> register(@RequestBody CustomerRegisterDto dto) {
        return response().execSuccess(customerService.register(dto));
    }

    @GetMapping("/{userId}")
    public ResponseWrapper<CustomerDetailResult> me(@PathVariable Long userId) {
        return response().execSuccess(customerService.userInfo(userId));
    }
}
