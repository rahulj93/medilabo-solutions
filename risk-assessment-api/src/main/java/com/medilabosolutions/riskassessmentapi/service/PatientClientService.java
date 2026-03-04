package com.medilabosolutions.riskassessmentapi.service; 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.medilabosolutions.riskassessmentapi.model.PatientClient;

import reactor.core.publisher.Mono;


@Service
public class PatientClientService {

    private final WebClient webClient; 
    private final String patientApiUrl; 

    public PatientClientService(WebClient webClient, @Value("${PATIENT_API_URL}") String patientApiUrl) {
        this.webClient = webClient; 
        this.patientApiUrl = patientApiUrl; 
        // this.patientApiUrl = System.getenv("PATIENT_API_URL"); 
        System.out.println("patient api url system variable: " + patientApiUrl);
        if (this.patientApiUrl == null) {
            throw new IllegalStateException("PATIENT_API_URL environment variable is not set");
        }       
    }

    public PatientClient lookupPatient(String id) {
        return webClient.get()
            .uri(patientApiUrl + "/patient/{id}", id)
            .retrieve()
            .onStatus(status -> status.value() == 404, 
                response -> Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient not found with id " + id
                )))
            .bodyToMono(PatientClient.class)
            .block(); 
    }
}
