package com.medilabosolutions.notesapi.repository; 

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medilabosolutions.notesapi.model.Note;

public interface NotesRepository extends MongoRepository<Note, String> {
    
    // Note findNoteByPatId(String patId); 
    // List<Note> findNotesByPatient(String patient); 
}
