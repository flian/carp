package org.lotus.carp.api.config.security;

import org.lotus.carp.api.config.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * API spring security 配置
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 12:57 PM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
     */
    @Resource(name = "jwtUserService")
    private UserDetailsService userDetailsService;

    @Value("${carp.api.enableCrossDomain}")
    private boolean enableCrossDomain = false;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //添加jwt filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                //swagger，全部放行
                .antMatchers(
                        "/public/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/csrf",
                        "/swagger-ui.html").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers(
                        "/auth/**",
                        "/api/wx/oauth2/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();
        //允许跨域
        if(enableCrossDomain){
            httpSecurity.headers().frameOptions().disable();
        }
    }
}
