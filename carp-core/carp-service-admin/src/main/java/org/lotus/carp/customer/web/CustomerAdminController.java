package org.lotus.carp.customer.web;

import org.lotus.carp.base.utils.excel.JxlsTemplate;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.customer.service.CustomerService;
import org.lotus.carp.customer.vo.CustomerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/30/2017
 * Time: 11:21 AM
 */

@Controller
@RequestMapping("/customer")
public class CustomerAdminController implements BaseController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String index() {
        return buildPage("customer/index");
    }

    private String buildPage(String url) {
        return String.format("%s", url);
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<CustomerResult> queryCustomers(@RequestParam("keyword") String q, Pageable page) {
        return response().execSuccess(customerService.search(q, page));
    }

    @PutMapping("/{userName}/password")
    @ResponseBody
    public ResponseWrapper<CustomerResult> changeCustomerPassword(@PathVariable("userName") String userName,@RequestBody String password){
        return response().execSuccess(customerService.changePassword("admin",userName,password));
    }

    @GetMapping("/export")
    public void export(@RequestParam("keyword") String q, @RequestParam("pageSize") Integer pageSize,
                       HttpServletResponse response) throws IOException {
        String excelTemplate ="customers.xls";
        List<CustomerResult> list = customerService.search(q, PageRequest.of(0,pageSize)).getContent();
        Map<String, Object> params = new HashMap<>();
        params.put("customers",list);
        response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
        JxlsTemplate.processTemplate(excelTemplate, response.getOutputStream(), params);
    }
}
