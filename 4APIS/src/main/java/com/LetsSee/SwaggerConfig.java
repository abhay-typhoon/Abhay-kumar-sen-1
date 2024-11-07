package com.LetsSee;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("employee-management")
                .pathsToMatch("/**")  // Define which paths you want to include in the API documentation
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System API")
                        .version("v1.0")
                        .description("API documentation for managing employees and departments.")
                        .termsOfService("Terms of service URL")
                        .contact(new Contact()
                                .name("Your Name")
                                .url("www.example.com")
                                .email("email@example.com"))
                        .license(new License()
                                .name("License of API")
                                .url("API license URL")));
    }
}
