package com.woof.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    // TODO: 수정하기

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage("com.example.demo.member")
                                .or(RequestHandlerSelectors.basePackage("com.example.demo.product"))
                )
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("Woof-Animal-Careservice", "v1.0"));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "기능 설명",
                version,
                "http://www.alittlevanilla.kro.kr/",
                new Contact("블로그 주소", "https://blog.naver.com/ghdalswl77", "ghdalswl77@naver.com"),
                "Licenses",
                "http://www.alittlevanilla.kro.kr/",
                new ArrayList<>());
    }


}
