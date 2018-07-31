package org.lotus.carp.configuration.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *  spring security 登录失败handler
 *
 * @author: Foy Lian
 * Date: 11/29/2017
 * Time: 4:31 PM
 */
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
     * returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the
     * exception for use in the target view.
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = encodeString("登录失败!");
        if (exception instanceof BadCredentialsException) {
            msg = encodeString("用户名密码错误！");
        }
        if (exception instanceof AuthenticationServiceException) {
            msg = encodeString(exception.getMessage());
        }
        redirectStrategy.sendRedirect(request, response, String.format("/login?msg=%s", msg));
    }

    private String encodeString(String s) throws UnsupportedEncodingException {
        return new String(s.getBytes(), "iso-8859-1");
    }
}
