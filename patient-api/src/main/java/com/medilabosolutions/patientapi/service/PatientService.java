package com.medilabosolutions.patientapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.medilabosolutions.patientapi.model.Patient;
import com.medilabosolutions.patientapi.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository; 
    }

    // public List<Patient> getPatients() {
    //     return patientRepository.getPatients(); 
    // }

    public Patient getPatient(String lastName, String firstName) {
        // return patientRepository.getPatient(lastName, firstName);
        return patientRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    // public Patient updatePatient(String lastName, String firstName) {
    //     return patientRepository.updatePatient(lastName, firstName);
    // }

    // public List<Patient> deletePatient(String lastName, String firstName) {
    //     return patientRepository.deletePatient(lastName, firstName);
    // }
}
