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
    private final String patientApiUrl; 

    public NotesService(NotesRepository notesRepository, WebClient webClient) {
        this.notesRepository = notesRepository; 
        this.webClient = webClient; 
        this.patientApiUrl = System.getenv("PATIENT_API_URL");
    }

    // public Note findById(String id) {
    //     return notesRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found with id " + id)); 
    // }
    public Note findById(String id) {
        return notesRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note not found with id " + id
            )); 
    }

    public Patient lookupPatient(String id) {
        System.out.println(id);
        return webClient.get()
            .uri(patientApiUrl + "/patient/{id}", id)
            .retrieve()
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

    public Note save(String patientId, Note incomingNote) {

        // 1. Verify patient exists (throws 404 if not found)
        Patient existingPatient = lookupPatient(patientId);

        // After looking up the patient by his/her id, set the name in note to the same name found patients DB. 
        String patientName = existingPatient.getFirstName() + " " + existingPatient.getLastName(); 
        // System.out.println(patientName);   
        incomingNote.setPatient(patientName);
        
        // 2. Check if notes document already exists
        Note existingNote = notesRepository.findById(patientId).orElse(null);
    
        if (existingNote != null) {
            // 3A. Append new notes to existing list
            existingNote.getNotes().addAll(incomingNote.getNotes());
            return notesRepository.save(existingNote);
        }
    
        // 3B. If no document exists → create new one
        incomingNote.setId(patientId);
        return notesRepository.save(incomingNote);
    }
}


// change note field to array type instead of string andd force unique id (patient id)

// actually don't worry about update or delete endpoints. just add a new note

// try to replace _id with patient id and ensure it's unique 