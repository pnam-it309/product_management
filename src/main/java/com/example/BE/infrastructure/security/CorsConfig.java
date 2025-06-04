package com.example.BE.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("*"); // Specify allowed origins
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed HTTP methods
//                .allowCredentials(true) // Allow sending credentials (cookies, authorization headers)
//                .maxAge(3600); // Cache preflight response for 1 hour
    }
}