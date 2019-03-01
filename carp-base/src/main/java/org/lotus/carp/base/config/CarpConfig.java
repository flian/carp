package org.lotus.carp.base.config;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
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
    /**
     * 默认页面的title
     */
    private String defaultTitle;
    /**
     * 项目名称,各种地方用到
     */
    private String projectName;
    /**
     * 是否显示导航上没有实现的链接
     */
    private boolean showUnusedIcons;
    /**
     * 登录页面，默认用户名
     */
    private String loginUserName;
    /**
     * 登录页面，默认密码
     */
    private String loginUserPassword;
    /**
     * 项目版本号
     */
    private String version;
    /**
     * 登录页面的字
     */
    private String loginText;
    /**
     * 项目版本号
     */
    private String versionStatus;
    /**
     * 需要导出到freemarker页面使用的bean
     */
    private String[] exposeBeanNames;
    /**
     * 需要导出到freemarker页面使用的静态类
     */
    private String[] exposeStaticClasses;
    /**
     * 设置框架风格：adminLTE或者huiAdmin
     */
    private String theme = themes[0];
    /**
     * 允许任何情况下的跨域
     */
    private boolean disableFrameOptions = false;
    /**
     * 只允许同域跨域
     */
    private boolean sameOrigin = false;

    private boolean userCanChangeTheme = true;

    /**
     * 是否是默认风格。 adminLTE是默认风格
     *
     * @return
     */
    public boolean isDefaultTheme() {
        String userTheme = CurrentUser.theme();
        if (Strings.isNotEmpty(userTheme) && themes[0].equals(userTheme)) {
            return true;
        }
        return themes[0].equals(theme);
    }

    /**
     * 是否是huiAdmin风格
     *
     * @return
     */
    public boolean isHuiAdminTheme() {
        String userTheme = CurrentUser.theme();
        if (Strings.isNotEmpty(userTheme) && themes[1].equals(userTheme)) {
            return true;
        }
        return themes[1].equals(theme);
    }

    /**
     * 修改风格
     */
    public void changeThemes(int what) {
        if (userCanChangeTheme) {
            if (what > themes.length - 1) {
                return;
            }
            CurrentUser.updateTheme(themes[what]);
            //theme = themes[what];
        }
    }

    public String[] themesDisplayNames() {
        return themes;
    }
}
