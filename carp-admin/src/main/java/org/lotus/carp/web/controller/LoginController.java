package org.lotus.carp.web.controller;

import com.google.code.kaptcha.Producer;
import org.lotus.carp.configuration.security.CaptchaUsernamePasswordAuthenticationFilter;
import org.lotus.carp.utils.Constants;
import org.lotus.carp.utils.ProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/29/2017
 * Time: 11:13 AM
 */
@Controller
public class LoginController {
    @Autowired
    private CaptchaUsernamePasswordAuthenticationFilter captchaUsernamePasswordAuthenticationFilter;

    @Autowired
    protected Producer kaptchaProducer;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("needCaptcha", captchaUsernamePasswordAuthenticationFilter.isCurrentRequestNeedCaptcha());
        return "security/login";
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ModelAndView loginError(AuthenticationServiceException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e.getMessage());
        mv.addObject("needCaptcha", captchaUsernamePasswordAuthenticationFilter.isCurrentRequestNeedCaptcha());
        mv.setViewName("security/login");
        return mv;
    }

    @GetMapping("/login/captcha/image")
    public void loginCaptcha(HttpServletResponse response) throws Exception {
        renderCaptcha(response, Constants.KAPTCHA_SESSION_KEY);
    }

    protected void renderCaptcha(HttpServletResponse response, String sessionKey) throws IOException {
        response.setDateHeader("Expires", 0);
        // 设置标准的 HTTP/1.1 no-cache 头
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展 HTTP/1.1 no-cache 头
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准HTTP/1.0 no-cache 头.
        response.setHeader("Pragma", "no-cache");
        // 图片格式image/jpeg
        response.setContentType("image/jpeg");
        //用Kaptcha生成器产生一个文本字符串
        String capText = kaptchaProducer.createText();
        // 将文本字符串内容存入session
        /*request.getSession().setAttribute(sessionKey, capText);*/
        ProfileUtil.session().setAttribute(sessionKey, capText);
        //创建含文本的图片
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 画出此图片并展示到页面上（request.getOutputStream)
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
