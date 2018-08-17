package org.lotus.carp.base.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.lotus.carp.base.utils.CarpConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Druid datasource configuration
 *
 * @author: Foy Lian
 * Date: 11/15/2017
 * Time: 2:12 PM
 */
@Configuration
@ConditionalOnClass(value = {DruidDataSource.class})
@EnableConfigurationProperties(DruidProperties.class)
@Slf4j
public class DruidDataSourceAutoConfiguration {
    @Autowired
    DruidProperties druidProperties;
    @Bean
    @Profile(CarpConstant.DEV)
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", druidProperties.getUsername());
        reg.addInitParameter("loginPassword", druidProperties.getPassword());
        reg.addInitParameter("logSlowSql", druidProperties.getLogSlowSql());
        reg.addInitParameter("allow", druidProperties.getAllow());
        return reg;
    }

    @Bean
    @Profile(CarpConstant.DEV)
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        BeanUtils.copyProperties(druidProperties, datasource);
        //use utf8mb4
        List<String> connectionInitSqls = new ArrayList<>();
        connectionInitSqls.add("set names utf8mb4;");
        datasource.setConnectionInitSqls(connectionInitSqls);
        try {
            datasource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        return datasource;
    }
}
