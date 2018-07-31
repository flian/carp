package org.lotus.carp.configuration;

import lombok.Setter;
import org.lotus.carp.base.config.CarpConfig;
import org.lotus.carp.configuration.security.ActionFilterSecurityMetadataSource;
import org.lotus.carp.configuration.security.AuthFailureHandler;
import org.lotus.carp.configuration.security.CaptchaUsernamePasswordAuthenticationFilter;
import org.lotus.carp.configuration.security.CarpRoleVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Foy Lian
 * Date: 8/3/2017
 * Time: 10:47 AM
 */
@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:/config/carp.properties")
@ConfigurationProperties(prefix = "carp.security.kaptcha")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ActionFilterSecurityMetadataSource actionFilterSecurityMetadataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Resource(name = "carpConfig")
    private CarpConfig carpConfig;

    @Setter
    private boolean captchaEnable = true;
    @Setter
    private int captchaFailedTimes = -1;

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new CarpRoleVoter(),
                new WebExpressionVoter(),
                new AuthenticatedVoter()
        );
        return new AffirmativeBased(decisionVoters);
    }

    @Bean
    public CaptchaUsernamePasswordAuthenticationFilter formLogin() throws Exception {
        CaptchaUsernamePasswordAuthenticationFilter filter = new CaptchaUsernamePasswordAuthenticationFilter(captchaEnable, captchaFailedTimes);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(authFailureHandler);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(formLogin(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .accessDecisionManager(accessDecisionManager())
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(actionFilterSecurityMetadataSource);
                        return fsi;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //使用hui-admin主题
        if (shouldEnableFrameCrossDomain()) {
            //允许frame调用
            if (carpConfig.isDisableFrameOptions()) {
                http.headers().frameOptions().disable();
            } else {
                http.headers().frameOptions().sameOrigin();
            }

        }
    }

    private boolean shouldEnableFrameCrossDomain() {
        if (null == carpConfig) {
            return false;
        }
        if (carpConfig.isSameOrigin()) {
            return true;
        }
        if (carpConfig.isHuiAdminTheme()) {
            return true;
        }
        if (carpConfig.isDisableFrameOptions()) {
            return true;
        }
        return false;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
