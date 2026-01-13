package com.medilabosolutions.patientapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.patientapi.model.Patient;
import com.medilabosolutions.patientapi.service.PatientService;

@RestController
public class PatientController {

    private final PatientService patientService; 

    public PatientController(PatientService patientService) {
        this.patientService = patientService; 
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients(); 
    }

    @GetMapping("/patient")
    public Patient getPatient(String lastName, String firstName) {
        return patientService.getPatient(lastName, firstName);  
    } 
    
    @PutMapping("/patient")
    public Patient updatePatient(String lastName, String firstName) {
        return patientService.updatePatient(lastName, firstName);  
    }

    @DeleteMapping("/patient")
    public List<Patient> deletePatient(String lastName, String firstName) {
        return patientService.deletePatient(lastName, firstName);
    }
}
