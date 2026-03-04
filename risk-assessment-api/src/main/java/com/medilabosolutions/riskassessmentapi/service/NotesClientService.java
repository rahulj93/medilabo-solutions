package com.medilabosolutions.riskassessmentapi.service; 

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.medilabosolutions.riskassessmentapi.model.NotesClient; 

import reactor.core.publisher.Mono;

@Service
public class NotesClientService {
    private final WebClient webClient; 
    private final String notesApiUrl; 

    public NotesClientService(WebClient webClient) {
        this.webClient = webClient; 
        this.notesApiUrl = System.getenv("NOTES_API_URL");
        System.out.println("notes api url system variable: " + notesApiUrl);

    }

    public NotesClient lookupNotes(String id) {
        return webClient.get()
            .uri(notesApiUrl + "/notes?id={id}", id)
            .retrieve()
            .onStatus(status -> status.value() == 404, 
                response -> Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Notes not found for patient with id " + id
                )))
            .bodyToMono(NotesClient.class)
            .block(); 
    }
}
