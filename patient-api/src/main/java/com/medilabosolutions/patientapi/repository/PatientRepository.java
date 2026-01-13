package com.medilabosolutions.patientapi.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.medilabosolutions.patientapi.model.Patient;

@Repository
public class PatientRepository {
    Patient patient1 = new Patient("Patient", "Test", "12-Jan-2026", "M", "123 VS Code Rd", "111-111-1111"); 
    Patient patient2 = new Patient("Patient2", "Test2", "13-Jan-2026", "F", "456 VS Code Rd", "000-000-0000"); 
    List<Patient> patients = new ArrayList<Patient>(Arrays.asList(patient1, patient2)); 

    public List<Patient> getPatients() {
        return patients; 
    }

    public Patient getPatient(String lastName, String firstName) {
        // logic to be added. this is a placeholder for now 
        return new Patient(lastName, firstName, "12-Jan-2026", "M", "123 VS Code Rd", "111-111-1111"); 
    } 

    public Patient updatePatient(String lastName, String firstName) {
        return new Patient(); 
    }

    public List<Patient> deletePatient(String lastName, String firstName) {
        return new ArrayList<>(); 
    }
}
