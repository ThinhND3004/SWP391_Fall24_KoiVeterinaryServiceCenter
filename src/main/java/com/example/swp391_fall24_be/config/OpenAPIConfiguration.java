package com.example.swp391_fall24_be.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

        @Value("${openapi.server.local}")
        private String localServerUrl;

        @Value("${openapi.server.product}")
        private String productServerUrl;

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("Koi Veterinarian Service Center OpenApi Specification")
                                .description("OpenApi documentation for Koi Veterinarian Service Center")
                                .version("1.0"))
                        .addServersItem(new Server().description("Local").url(localServerUrl))
                        .addServersItem(new Server().description("Product").url(productServerUrl))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .schemaRequirement("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .description("JWT auth")
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER));
        }
}