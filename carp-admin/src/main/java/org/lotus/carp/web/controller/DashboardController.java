package org.lotus.carp.web.controller;

import com.google.code.kaptcha.Producer;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.security.convter.MenuConverter;
import org.lotus.carp.security.service.impl.MenuServiceImpl;
import org.lotus.carp.security.vo.MenuResult;
import org.lotus.carp.utils.Constants;
import org.lotus.carp.utils.ProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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

    @Autowired
    protected Producer kaptchaProducer;

    @GetMapping(value = {"", "/index", "/home", "/dashboard"})
    public String index() {
        return "/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
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
    public String error() {
        return "超人，出错啦！ 我们马上回来。";
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
