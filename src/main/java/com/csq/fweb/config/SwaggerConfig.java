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
                       .title("STUDENT INFORMATION MANAGEMENT API")
                       .description("Provide addition, deletion, modification and query operations of student information")
                       .version("1.0.0"));
    }
}