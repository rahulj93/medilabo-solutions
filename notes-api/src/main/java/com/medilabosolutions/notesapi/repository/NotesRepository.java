package com.medilabosolutions.notesapi.repository; 

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medilabosolutions.notesapi.model.Note;

public interface NotesRepository extends MongoRepository<Note, String> {
    
    List<Note> findByPatId(String patId); 
    // List<Note> findByPatient(String patient); 
}

/*

- don't give option to find by patient, only find by patient id 

- for next meeting : 

    - finish spring 2
    - add sprint 3 within sprint 2 controller 

eg. user wants to add a note: 

they need to enter patient id and the note as params 
- id will be used to verify patient in Postgres table 

- add note for patient with id 1 
-> it will find the patient in patients db (in this case, John Doe)
    -> have notes microservice call the patients microservice 
-> add the entry to the Notes db with the same id replacing _id field 

- update/delete note: 
-> look up by id and do the action 
-> if entry doesnt exist, return a 400 error message (not found)


next meeting at 3pm on saturday 

*/
