package com.medilabosolutions.patientapi.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.medilabosolutions.patientapi.model.Patient;

@RestController
public class PatientController {

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        Patient patient1 = new Patient("Patient", "Test", "12-Jan-2026", "M", "123 VS Code Rd", "111-111-1111"); 
        Patient patient2 = new Patient("Patient2", "Test2", "13-Jan-2026", "F", "456 VS Code Rd", "000-000-0000"); 

        return new ArrayList<Patient>(Arrays.asList(patient1, patient2)); 
    }

    @GetMapping("/patient")
    public Patient getPatient(String lastName, String firstName) {
        // logic to be added. this is a placeholder for now 
        return new Patient(lastName, firstName, "12-Jan-2026", "M", "123 VS Code Rd", "111-111-1111"); 
    } 
    
    @PutMapping("/patient")
    public Patient updatePatient(String lastName, String firstName) {
        return new Patient(); 
    }

    @DeleteMapping("/patient")
    public List<Patient> deletePatient(String lastName, String firstName) {
        return new ArrayList<>(); 
    }

}
