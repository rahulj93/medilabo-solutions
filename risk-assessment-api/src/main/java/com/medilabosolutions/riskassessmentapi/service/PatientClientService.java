package com.medilabosolutions.riskassessmentapi.service; 

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.medilabosolutions.riskassessmentapi.model.PatientClient;

import reactor.core.publisher.Mono;


@Service
public class PatientClientService {

    private final WebClient webClient; 

    public PatientClientService(WebClient webClient) {
        this.webClient = webClient; 
    }

    public PatientClient lookupPatient(String id) {
        return webClient.get()
            .uri("http://localhost:8080/patient/{id}", id)
            .retrieve()
            .onStatus(status -> status.value() == 404, 
                response -> Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient not found with id " + id
                )))
            .bodyToMono(PatientClient.class)
            .block(); 
    }
}
