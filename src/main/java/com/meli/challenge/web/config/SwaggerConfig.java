package com.meli.challenge.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String PROJECT_NAME = "Challenge - Mercadolibre";
    public static final String PROJECT_VERSION = "1.0";
    public static final String PROJECT_DESCRIPTION = "Prueba de desarrollo";

    @Bean
    public Docket document() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.meli.challenge.web"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title(PROJECT_NAME)
                .version(PROJECT_VERSION)
                .description(PROJECT_DESCRIPTION)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }


}
