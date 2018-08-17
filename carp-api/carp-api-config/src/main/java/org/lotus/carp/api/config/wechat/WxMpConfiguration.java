package org.lotus.carp.api.config.wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/16/2018
 * Time: 5:24 PM
 */
@Configuration
@ConditionalOnProperty(value = "wechat.enable", havingValue = "true")
@ConditionalOnClass(value = {WxMpInMemoryConfigStorage.class, WxMpServiceImpl.class})
@EnableConfigurationProperties(WxMpProperties.class)
@Slf4j
public class WxMpConfiguration {
    @ConditionalOnMissingBean(value = {WxMpService.class})
    @Bean
    public WxMpService createWxMpService(WxMpProperties wxMpProperties) {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(wxMpProperties.getAppId());
        config.setSecret(wxMpProperties.getAppSecret());
        config.setOauth2redirectUri(wxMpProperties.getOauth2RedirectUri());
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);
        return wxService;
    }
}
