package com.medilabosolutions.notesapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(params= "patId")
    public List<Note> getNotesByPatId(String patId) {
        return notesService.getByPatId(patId); 
    }


    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable String id) {
        return notesService.lookupPatient(id); 
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.findAll(); 
    }

    @PostMapping("/{id}") // make sure to add id and verify that patient exists in Patients db 
    public ResponseEntity<Note> createNote(@PathVariable String id, @RequestBody Note note) {
        Note saved = notesService.save(note); 
        return new ResponseEntity<>(saved, HttpStatus.CREATED); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note note) {
        Note updated = notesService.update(id, note);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletenote(@PathVariable String id) {
        notesService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }


}

/*
- need patient id, not patient here 

*/
