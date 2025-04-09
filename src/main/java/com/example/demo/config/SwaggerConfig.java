//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public OpenAPI api() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Hotel Management API")
//                        .description("API for login, register, and role-based access with JWT")
//                        .version("v1.0"))
//                .components(new Components()
//                        .addSecuritySchemes("bearerAuth",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
//}
