package org.lotus.carp.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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
public class CarpConfig implements Serializable {
    private static final long serialVersionUID = -8360122653581869435L;
    private static String[] themes = {"adminLTE", "huiAdmin"};
    private String defaultTitle;
    private String projectName;
    private boolean showUnusedIcons;
    private String loginUserName;
    private String loginUserPassword;
    private String version;
    private String loginText;
    private String versionStatus;
    private String[] exposeBeanNames;
    private String[] exposeStaticClasses;
    private String theme = themes[0];

    public boolean isDefaultTheme() {
        return themes[0].equals(theme);
    }

    public boolean isHuiAdminTheme() {
        return themes[1].equals(theme);
    }
}
