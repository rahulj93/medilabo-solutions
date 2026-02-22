package com.medilabosolutions.notesapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.notesapi.model.Note;
import com.medilabosolutions.notesapi.model.Patient;
import com.medilabosolutions.notesapi.service.NotesService;


@RestController
@RequestMapping("/notes")
public class NotesController {

    NotesService notesService; 

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(params= "id")
    public Note getNotesById(String id) {
        return notesService.findById(id); 
    }


    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable String id) {
        return notesService.lookupPatient(id); 
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.findAll(); 
    }

    @PostMapping("/{id}") // make sure to add id and verify that patient exists in Patients db 
    public ResponseEntity<Note> createNote(@PathVariable String id, @RequestBody Note note) {
        Note saved = notesService.save(id, note); 
        return new ResponseEntity<>(saved, HttpStatus.CREATED); 
    }
}

/*
- need patient id, not patient here 

*/
