package com.kruger.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {
    // @Bean
    // Docket api(){
    //     return new Docket(DocumentationType.SWAGGER_2).select()
    //     .apis(RequestHandlerSelectors.basePackage("com.kruger.microservice"))
    //     .build()
    //     .apiInfo(getApiInfo());
    // }
    
    @Bean
    public OpenAPI publicApi(){
        return new OpenAPI()
        .components(new Components())
        .info(new io.swagger.v3.oas.models.info.Info().title("Football Player")
        .description("Spring football player application")
        .version("v1.0"));
    }

}
