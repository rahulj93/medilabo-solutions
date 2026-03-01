package com.medilabosolutions.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(
                p -> p
                .path("/patients")
                .filters(f -> f.addRequestHeader("MedilaboSolutions", "Patients"))
                .uri("http://localhost:8080")
            )
            .route(
                p -> p
                .path("/patient/**")
                .filters(f -> f.addRequestHeader("Medilabo", "Patient"))
                .uri("http://localhost:8080"))
            .route(
                p -> p
                .path("/notes/**")
                .filters(f -> f.addRequestHeader("Patient", "Notes"))
                .uri("http://localhost:8081"))
            .route(
                p -> p
                .path("/notes")
                .filters(f -> f.addRequestHeader("Patient", "Notes"))
                .uri("http://localhost:8081"))
            .build(); 
    }
}
