package org.lotus.carp.base.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Carp 框架配置
 *
 * @author: Foy Lian
 * Date: 5/7/2018
 * Time: 4:47 PM
 */
@Configuration
@ConfigurationProperties(prefix = "carp.config")
@Data
@Component("carpConfig")
@ConditionalOnMissingBean(name = "carpConfig")
public class CarpConfig {
    private String defaultTitle;
    private String version;
    private String versionStatus;
    private String[] exposeBeanNames;
    private String[] exposeStaticClasses;
}
