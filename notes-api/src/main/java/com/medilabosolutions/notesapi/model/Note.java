package com.medilabosolutions.notesapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="notes")
public class Note {

    @Id 
    private String id; 

    private String patId; 
    private String note; 

    public Note(String patId, String note) {
        this.patId = patId; 
        this.note = note; 
    }

    public String getId() {
        return id; 
    } 

    public void setId(String id) {
        this.id = id; 
    }

    public String getPatId() {
        return patId; 
    } 

    public void setPatId(String patId) {
        this.patId = patId; 
    }

    public String getNote() {
        return note; 
    }

    public void setNote(String note) {
        this.note = note; 
    }
}

/* 
- we only want one id , not both (get rid of patId, keep Id)
- acutally don't mix with _id (internal id)... keep patId, get rid of Id  
- patient name field needed here 


*/ 
