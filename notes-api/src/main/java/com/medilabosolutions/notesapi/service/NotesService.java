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

    public List<Note> getByPatId(String patId) {
        return notesRepository.findByPatId(patId);
    }

    public List<Note> getByPatientName(String patient) {
        return notesRepository.findByPatient(patient);
    }

    public List<Note> findAll() {
        return notesRepository.findAll(); 
    }

    public Note save(Note note) {
        return notesRepository.save(note); 
    }

    public Note update(String id, Note note) {
        note.setId(id);
        return notesRepository.save(note); 
    }

    public void deleteById(String id) {
        notesRepository.deleteById(id);
    }
}
