package com.medilabosolutions.notesapi.repository; 

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medilabosolutions.notesapi.model.Note;

public interface NotesRepository extends MongoRepository<Note, String> {
    
    List<Note> findByPatId(String patId); 
    List<Note> findByPatient(String patient); 
}
