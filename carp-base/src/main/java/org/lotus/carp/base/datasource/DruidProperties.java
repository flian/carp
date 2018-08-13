package org.lotus.carp.base.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  Druid datasource properties
 *
 * @author: Foy Lian
 * Date: 8/13/2018
 * Time: 5:31 PM
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private String filters;
    private String logSlowSql;
    private String allow;
}
