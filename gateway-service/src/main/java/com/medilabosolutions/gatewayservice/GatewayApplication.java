package com.medilabosolutions.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    @Value("${PATIENT_API_URL}")
    private String patientApiUrl;

    @Value("${NOTES_API_URL}")
    private String notesApiUrl;

    @Value("${RISK_ASSESSMENT_API_URL}")
    private String riskAssessmentApiUrl;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(
                p -> p
                .path("/patients")
                // .filters(f -> f.addRequestHeader("MedilaboSolutions", "Patients"))
                .uri(patientApiUrl)
            )
            .route(
                p -> p
                .path("/patient/**")
                // .filters(f -> f.addRequestHeader("Medilabo", "Patient"))
                .uri(patientApiUrl))
            .route(
                p -> p
                .path("/notes/**")
                // .filters(f -> f.addRequestHeader("Patient", "Notes"))
                .uri(notesApiUrl))
            .route(
                p -> p
                .path("/notes")
                // .filters(f -> f.addRequestHeader("Patient", "Notes"))
                .uri(notesApiUrl))
            .route(
                p -> p
                .path("/risk-assessment/**")
                // .filters(f -> f.addRequestHeader("Patient", "Risk Assessment"))
                .uri(riskAssessmentApiUrl))
            .build(); 
    }
}
