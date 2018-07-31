package org.lotus.carp.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security 基础配置
 *
 * @author: Foy Lian
 * Date: 7/31/2018
 * Time: 5:20 PM
 */
@Configuration

public class SpringSecurityBaseConfig {
    @Bean
    @ConditionalOnClass(value = {PasswordEncoder.class, BCryptPasswordEncoder.class})
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
