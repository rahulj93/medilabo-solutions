package com.medilabosolutions.notesapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.notesapi.model.Note;
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

    @GetMapping(params = "patient")
    public List<Note> getNotesByPatientName(String patient) {
        return notesService.getByPatientName(patient);  
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.findAll(); 
    }

}
