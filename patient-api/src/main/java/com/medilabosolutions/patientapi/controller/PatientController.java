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

/**
 * REST controller for managing patients.
 * 
 * Provides endpoints to create, read, update, and delete patient records.
 */
@RestController
public class PatientController {

    private final PatientService patientService; 

    /**
     * Constructs a PatientController with the given PatientService.
     * 
     * @param patientService the service layer handling patient operations
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService; 
    }

    /**
     * Retrieves a list of all patients.
     * 
     * @return a List of Patient objects
     */    
    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients(); 
    }

    /**
     * Retrieves a single patient by their ID.
     * 
     * @param id the ID of the patient to retrieve
     * @return the Patient object with the given ID
     */
    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id); 
    }

    /**
     * Creates a new patient record.
     * 
     * @param patient the Patient object to create
     * @return the newly created Patient object
     */    
    @PostMapping("/patient")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    /**
     * Updates an existing patient record.
     * 
     * @param id the ID of the patient to update
     * @param patient the Patient object containing updated data
     * @return the updated Patient object
     */
    @PutMapping("/patient/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient
    ) {
        return patientService.updatePatient(id, patient);
    }
    
    /**
     * Deletes a patient by their ID.
     * 
     * @param id the ID of the patient to delete
     */
    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
