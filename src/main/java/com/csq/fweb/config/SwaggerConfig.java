package com.csq.fweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
               .info(new Info()
                       .title("学生信息管理 API")
                       .description("提供学生信息的增删改查操作")
                       .version("1.0.0"));
    }
}