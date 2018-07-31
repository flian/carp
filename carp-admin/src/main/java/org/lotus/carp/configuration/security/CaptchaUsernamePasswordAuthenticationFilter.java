package org.lotus.carp.configuration.security;

import com.google.common.base.Strings;
import org.lotus.carp.utils.ProfileUtil;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 带可选验证码的登录filter
 *
 * @author: Foy Lian
 * Date: 11/28/2017
 * Time: 5:06 PM
 */
public class CaptchaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean captchaEnable = true;
    private int captchaFailedTimes = -1;
    private static final String PASSWORD_FAILED_TIMES_KEY = "_captcha_password_failed_times";
    public static final String DEFAULT_CAPTCHA_CODE_PARAMETER = "captchaCode";

    public CaptchaUsernamePasswordAuthenticationFilter() {
    }

    public CaptchaUsernamePasswordAuthenticationFilter(boolean captchaEnable, int captchaFailedTimes) {
        this.captchaEnable = captchaEnable;
        this.captchaFailedTimes = captchaFailedTimes;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!isCurrentRequestNeedCaptcha()) {
            //不需要验证验证码
            failedTimesPlus();
            return super.attemptAuthentication(request, response);
        }

        failedTimesPlus();
        String userInputCaptchaCode = obtainCaptchaCode(request);
        String capTxt = ProfileUtil.getLoginCapText();
        if (Strings.isNullOrEmpty(userInputCaptchaCode)) {
            throw new AuthenticationServiceException("请输入验证码!");
        }
        if (Strings.isNullOrEmpty(capTxt)) {
            throw new AuthenticationServiceException("找不到验证码!");
        }
        if (!capTxt.equalsIgnoreCase(userInputCaptchaCode)) {
            throw new AuthenticationServiceException("输入的验证码不正确，请重新输入!");
        }
        return super.attemptAuthentication(request, response);
    }

    private int failedTimes() {
        Integer tmp = (Integer) ProfileUtil.session().getAttribute(PASSWORD_FAILED_TIMES_KEY);
        return null == tmp ? 0 : tmp;
    }

    private void failedTimesPlus() {
        ProfileUtil.session().setAttribute(PASSWORD_FAILED_TIMES_KEY, failedTimes() + 1);
    }

    private String obtainCaptchaCode(HttpServletRequest request) {
        return request.getParameter(DEFAULT_CAPTCHA_CODE_PARAMETER);
    }

    public boolean isCurrentRequestNeedCaptcha() {
        if (!captchaEnable || -1 == captchaFailedTimes || failedTimes() < captchaFailedTimes) {
            return false;
        }
        return true;
    }
}
