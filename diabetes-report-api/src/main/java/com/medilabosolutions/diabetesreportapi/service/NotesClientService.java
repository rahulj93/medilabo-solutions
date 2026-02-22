package com.medilabosolutions.diabetesreportapi.service; 

import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.stereotype.Service;

import com.medilabosolutions.diabetesreportapi.model.NotesClient; 

@Service
public class NotesClientService {
    private final WebClient webClient; 

    public NotesClientService(WebClient webClient) {
        this.webClient = webClient; 
    }

    public NotesClient lookupNotes(String id) {
        return new NotesClient(); 
    }
}
