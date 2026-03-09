package com.medilabosolutions.patientapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id); 
    }
    
    @PostMapping("/patient")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/patient/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient
    ) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
