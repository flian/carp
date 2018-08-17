package org.lotus.carp.api.config.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端用户帮助类
 *
 * @author: Foy Lian
 * Date: 8/17/2018
 * Time: 5:08 PM
 */
public class CustomerUtil {
    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String name() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static HttpServletRequest request() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
