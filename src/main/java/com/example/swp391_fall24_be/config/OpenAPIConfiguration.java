package com.example.swp391_fall24_be.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Info information = new Info()
                .title("Koi Veterinary Service Center API")
                .version("1.0")
                .description("This API exposes endpoints to manage Koi Veterinary Service Center.");
        return new OpenAPI().info(information);
    }
}