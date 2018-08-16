package org.lotus.carp.api.config.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/16/2018
 * Time: 5:20 PM
 */
@Data
@ConfigurationProperties(prefix = "wechat")
public class WxMpProperties {
    private String appId;
    private String appSecret;
    private String oauth2RedirectUri;
}
