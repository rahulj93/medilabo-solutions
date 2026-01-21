package com.medilabosolutions.notesapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medilabosolutions.notesapi.model.Note;
import com.medilabosolutions.notesapi.repository.NotesRepository;

@Service
public class NotesService {

    private final NotesRepository notesRepository; 

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository; 
    }

    // public Note getNoteByPatId(String patId) {
    //     return notesRepository.findNoteByPatId(patId);
    // }

    public List<Note> findAll() {
        return notesRepository.findAll(); 
    }

}
