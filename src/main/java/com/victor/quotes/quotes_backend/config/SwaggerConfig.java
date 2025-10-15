package com.victor.quotes.quotes_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quotes Backend API")
                        .description("API para gestionar citas y frases inspiradoras")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Victor")
                                .email("victor.trimpa1987@gmail.com")));
    }
}