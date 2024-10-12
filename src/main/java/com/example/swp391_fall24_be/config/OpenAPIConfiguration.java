package com.example.swp391_fall24_be.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for Koi Veterian Service Center",
                title = "Koi Veterian Service Center OpenApi Specification",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Product",
                        url = "https://koiveterianservicenter.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfiguration {
}