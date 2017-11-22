package org.lotus.carp.utils;

import com.google.common.base.Preconditions;
import org.lotus.carp.profile.vo.MenuResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/22/2017
 * Time: 10:21 AM
 */
public class ProfileUtil {
    private static final String CACHED_USER_MENUS="_session_cached_user_menus";
    public static String name() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static List<MenuResult> userMenus() {
        return ( List<MenuResult>)request().getSession().getAttribute(CACHED_USER_MENUS);
    }

    public static void cacheUserMenus(List<MenuResult> userMenus) {
        Preconditions.checkNotNull(userMenus,"用户菜单不应该是null!");
        request().getSession().setAttribute(CACHED_USER_MENUS,userMenus);
    }

    public static HttpServletRequest request() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
