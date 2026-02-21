package com.medilabosolutions.notesapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.medilabosolutions.notesapi.model.Note;
import com.medilabosolutions.notesapi.model.Patient;
import com.medilabosolutions.notesapi.repository.NotesRepository;

import reactor.core.publisher.Mono;

@Service
public class NotesService {

    private final NotesRepository notesRepository; 

    private final WebClient webClient; 

    public NotesService(NotesRepository notesRepository, WebClient webClient) {
        this.notesRepository = notesRepository; 
        this.webClient = webClient; 
    }

    public List<Note> getByPatId(String patId) {
        return notesRepository.findByPatId(patId);
    }

    // public List<Note> getByPatientName(String patient) {
    //     return notesRepository.findByPatient(patient);
    // }
    public Patient lookupPatient(String id) {
        System.out.println(id);
        return webClient.get()
            .uri("http://localhost:8080/patient/{id}", id)
            .retrieve()
            // .onStatus(status -> status.value() >=400 && status.value() < 500, response -> {
            //     System.out.println("Patient not found: " + id);
            //     return Mono.empty();
            // })
        //     .onStatus(status -> status.value() == 404, 
        //         response -> Mono.error(new WebClientResponseException(
        //             "Patient not found with id " + id,
        // 404, "Not Found", null, null, null
        //     )))
            .onStatus(status -> status.value() == 404,
                response -> Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient not found with id " + id
                )))
            .bodyToMono(Patient.class)
            .block(); //blocking for simplicity 
            // .blockOptional()
            // .orElseThrow(() -> new ResponseStatusException(
            //     HttpStatus.NOT_FOUND, "Patient not found with id " + id
            // )); 
    }

    public List<Note> findAll() {
        return notesRepository.findAll(); 
    }

    public Note save(Note note) {
        return notesRepository.save(note); 
    }

    public Note update(String id, Note note) {
        Note existing = notesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    
        existing.setNote(note.getNote());
        existing.setPatId(note.getPatId());
    
        return notesRepository.save(existing);
    }
    

    public void deleteById(String id) {
        notesRepository.deleteById(id);
    }
}
