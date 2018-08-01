package org.lotus.carp.api.config;

import org.lotus.carp.base.utils.CarpConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.any;

/**
 * swagger 配置
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 12:15 PM
 */

@Configuration
@EnableSwagger2
@Profile(CarpConstant.DEV)
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(any())
                .paths(PathSelectors.regex("/api/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Carp RESTful API")
                .version("1.0")
                .build();
    }
}
