package org.lotus.carp.api.config.web;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.lotus.carp.api.config.dto.JwtAuthenticationResponse;
import org.lotus.carp.api.config.dto.WechatOauth2LoginUrlResult;
import org.lotus.carp.api.config.jwt.AuthService;
import org.lotus.carp.api.config.wechat.WxMpProperties;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.customer.enums.Gender;
import org.lotus.carp.customer.service.CustomerService;
import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerWechatRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/16/2018
 * Time: 5:31 PM
 */
@ConditionalOnProperty(value = "wechat.enable", havingValue = "true")
@RestController
@RequestMapping("/api/wx/oauth2")
@Slf4j
public class WechatOauth2ApiController implements BaseController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpProperties wxMpProperties;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/url")
    public ResponseWrapper<WechatOauth2LoginUrlResult> wxLoginUrl(@RequestParam(value = "oauth2RedirectUri", required = false, defaultValue = "") String redirectUri,
                                                                  HttpServletRequest request) {
        if (!isWechatRequest(request)) {
            log.info("seems call is not from wechat, may can't login success!");
        }
        String wxOauth2RedirectUri = Strings.isNullOrEmpty(redirectUri) ? wxMpProperties.getOauth2RedirectUri() : redirectUri;
        WechatOauth2LoginUrlResult result = new WechatOauth2LoginUrlResult();
        String url = wxMpService.oauth2buildAuthorizationUrl(wxOauth2RedirectUri, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        result.setUrl(url);
        return response().execSuccess(result);
    }

    @GetMapping("/oauth2RedirectUri")
    public ResponseWrapper<JwtAuthenticationResponse> wxOauth2CallBack(@RequestParam(value = "code") String wxCode) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(wxCode);
        Preconditions.checkArgument(wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken), "access token无效!");
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        Preconditions.checkArgument(Strings.isNullOrEmpty(wxMpUser.getOpenId())
                && Strings.isNullOrEmpty(wxMpUser.getUnionId()), "openId, unionId不能同时为空!");
        // save user, and login user
        CustomerWechatRegisterDto wechatRegisterDto = new CustomerWechatRegisterDto();
        wechatRegisterDto.setGender(Gender.parse(wxMpUser.getSex()));
        wechatRegisterDto.setNickName(wxMpUser.getNickname());
        wechatRegisterDto.setOpenId(wxMpUser.getOpenId());
        wechatRegisterDto.setUnionId(wxMpUser.getUnionId());
        wechatRegisterDto.setHeadIconUrl(wxMpUser.getHeadImgUrl());
        CustomerDetailResult customerDetailResult = customerService.registerFromWechat(wechatRegisterDto);
        //login and return jwt token
        return response().execSuccess(authService.loginWithUuid(customerDetailResult.getUuid()));
    }

    private boolean isWechatRequest(HttpServletRequest request) {
        boolean isWechat = false;
        String ua = request.getHeader("user-agent").toLowerCase();
        // 是微信浏览器
        if (ua.indexOf("micromessenger") > 0) {
            isWechat = true;
        }
        return isWechat;
    }
}
