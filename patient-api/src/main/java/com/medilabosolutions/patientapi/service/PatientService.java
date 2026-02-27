package com.medilabosolutions.patientapi.service;

import java.util.List;

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

    public List<Patient> getPatients() {
        return patientRepository.findAll(); 
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found")); 
    }

    public Patient getPatient(String lastName, String firstName) {
        // return patientRepository.getPatient(lastName, firstName);
        return patientRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = patientRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Patient not found"
            ));

        existing.setFirstName(updatedPatient.getFirstName());
        existing.setLastName(updatedPatient.getLastName());
        existing.setDateOfBirth(updatedPatient.getDateOfBirth());
        existing.setGender(updatedPatient.getGender());
        existing.setAddress(updatedPatient.getAddress());
        existing.setPhone(updatedPatient.getPhone());

        return patientRepository.save(existing);
    }
    
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
