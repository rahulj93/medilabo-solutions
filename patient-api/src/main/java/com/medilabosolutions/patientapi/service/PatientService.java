package com.medilabosolutions.patientapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medilabosolutions.patientapi.model.Patient;
import com.medilabosolutions.patientapi.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository; 
    }

    public List<Patient> getPatients() {
        return patientRepository.getPatients(); 
    }

    public Patient getPatient(String lastName, String firstName) {
        return patientRepository.getPatient(lastName, firstName);
    }

    public Patient updatePatient(String lastName, String firstName) {
        return patientRepository.updatePatient(lastName, firstName);
    }

    public List<Patient> deletePatient(String lastName, String firstName) {
        return patientRepository.deletePatient(lastName, firstName);
    }
}
