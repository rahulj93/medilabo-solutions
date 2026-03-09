package com.medilabosolutions.notesapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="notes")
public class Note {

    @Id 
    private String id; 

    private String patient; 

    private List<String> notes; 

    public Note(String id, String patient, List<String> notes) {
        this.id = id; 
        this.patient = patient; 
        this.notes = notes; 
    }

    public String getId() {
        return id; 
    } 

    public void setId(String id) {
        this.id = id; 
    }

    public String getPatient() {
        return patient; 
    } 

    public void setPatient(String patient) {
        this.patient = patient; 
    }

    public List<String> getNotes() {
        return notes; 
    }

    public void setNotes(List<String> notes) {
        this.notes = notes; 
    }
}