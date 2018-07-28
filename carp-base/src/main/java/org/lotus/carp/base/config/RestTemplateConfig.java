package org.lotus.carp.base.config;

import org.lotus.carp.base.config.logging.LoggingRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义一个全局的rest template对象
 *
 * @author: Foy Lian
 * Date: 6/26/2018
 * Time: 9:49 AM
 */
@Configuration
@ConditionalOnClass(value = {RestTemplate.class})
public class RestTemplateConfig {
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate globalRestTemplate() {
        SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();
        clientFactory.setConnectTimeout(20000);
        clientFactory.setReadTimeout(20000);
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(clientFactory));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
