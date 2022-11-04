package com.gerimedica.assignment.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Gerimedica Assignment Api service")
                .version("1.0.0")
                .description("This application provides api for building Gerimedica Assignment application.")
                .termsOfService("TERMS OF SERVICE URL")
                .license(new License().name("Saeid Azimi")
                        .url("https://linkedin.com/in/saeedazimi")));
    }
}
