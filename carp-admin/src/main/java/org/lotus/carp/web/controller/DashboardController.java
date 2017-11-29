package org.lotus.carp.web.controller;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.security.convter.MenuConverter;
import org.lotus.carp.security.service.impl.MenuServiceImpl;
import org.lotus.carp.security.vo.MenuResult;
import org.lotus.carp.utils.ProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : Foy Lian
 *         Date: 8/2/2017
 *         Time: 3:00 PM
 */
@Controller
public class DashboardController extends AdminBaseController implements AccessDeniedHandler, ErrorController {
    private static final String ERROR_PATH = "/error";
    private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private MenuConverter menuConverter;
    @Autowired
    private MenuServiceImpl menuService;


    @GetMapping(value = {"", "/index", "/home", "/dashboard"})
    public String index() {
        return "/dashboard";
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "没有权限！";
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public String error(Exception e) {
        return String.format("超人，出错啦！ 我们马上回来。- %s",e.getMessage());
    }


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

    }

    @GetMapping("/index/menus")
    @ResponseBody
    public ResponseWrapper<List<MenuResult>> userMenus() {
        List<MenuResult> list = ProfileUtil.userMenus();
        if (list == null) {
            list = menuConverter.buildTreeWithoutRoot(menuService.getMenusByUserName(ProfileUtil.name()));
            ProfileUtil.cacheUserMenus(list);
        }
        return response().execSuccess(list);
    }


}
