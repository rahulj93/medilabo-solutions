package com.medilabosolutions.diabetesreportapi.service; 

import org.springframework.web.reactive.function.client.WebClient;

import com.medilabosolutions.diabetesreportapi.model.PatientClient;

public class PatientClientService {

    private final WebClient webClient; 

    public PatientClientService(WebClient webClient) {
        this.webClient = webClient; 
    }

    public PatientClient lookupPatient(String id) {
        return new PatientClient(); 
    }

}
