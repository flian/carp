package org.lotus.carp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *  设置springMVC分页参数
 *
 * @author : Foy Lian
 * Date: 8/18/2017
 * Time: 5:02 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        //使用第一页pageNum = 1
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
    }

    /**
     * 获取验证器
     *
     * @return
     */
    @Override
    @Bean
    public SmartValidator getValidator() {
        return new LocalValidatorFactoryBean();
    }
}
