package com.medilabosolutions.riskassessmentapi.model;

import java.util.List;

public class NotesClient {

    private Long id; 

    String patient; 
    List<String> notes; 

    public NotesClient(String patient, List<String> notes) {
        this.patient = patient; 
        this.notes = notes; 
    }

    public String getPatient() {
        return patient; 
    }

    public List<String> getNotes() {
        return notes; 
    }
}
