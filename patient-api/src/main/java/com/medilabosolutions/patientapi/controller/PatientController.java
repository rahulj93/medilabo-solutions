package com.medilabosolutions.patientapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.patientapi.model.Patient;
import com.medilabosolutions.patientapi.service.PatientService;

@RestController
public class PatientController {

    private final PatientService patientService; 

    public PatientController(PatientService patientService) {
        this.patientService = patientService; 
    }

    // @GetMapping("/patients")
    // public List<Patient> getPatients() {
    //     return patientService.getPatients(); 
    // }
    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id); 
    }

    @GetMapping("/patient")
    public Patient getPatient(@RequestParam String lastName, @RequestParam String firstName) {
        return patientService.getPatient(lastName, firstName);  
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
    // @PutMapping("/patient")
    // public Patient updatePatient(String lastName, String firstName) {
    //     return patientService.updatePatient(lastName, firstName);  
    // }

    // @DeleteMapping("/patient")
    // public List<Patient> deletePatient(String lastName, String firstName) {
    //     return patientService.deletePatient(lastName, firstName);
    // }
}
