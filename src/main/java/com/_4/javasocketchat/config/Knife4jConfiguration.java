package com._4.javasocketchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class Knife4jConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                .title("接口文档")
                .version("0.1")
                .description("练手项目接口文档")
            );
    }
    @Bean
    public GroupedOpenApi controllerApi() {
    return GroupedOpenApi.builder()
            .group("controller")                 // 分组名称，可任意
            .packagesToScan("com._4.javasocketchat.controller")  // 扫描指定包
            .build();
    }

}
