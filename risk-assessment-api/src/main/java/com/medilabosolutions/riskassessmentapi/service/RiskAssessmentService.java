package com.medilabosolutions.riskassessmentapi.service; 

import org.springframework.stereotype.Service;

import com.medilabosolutions.riskassessmentapi.model.NotesClient;
import com.medilabosolutions.riskassessmentapi.model.PatientClient;
import com.medilabosolutions.riskassessmentapi.model.RiskAssessment;
import com.medilabosolutions.riskassessmentapi.util.AgeCalculator;
import com.medilabosolutions.riskassessmentapi.util.RiskLevelAssessor;

@Service
public class RiskAssessmentService {

    private final NotesClientService notesClientService; 
    private final PatientClientService patientClientService; 

    public RiskAssessmentService(NotesClientService notesClientService, PatientClientService patientClientService) {
        this.notesClientService = notesClientService; 
        this.patientClientService = patientClientService; 
    }

    public RiskAssessment getDiabetesReport(String id) {
        PatientClient patient = patientClientService.lookupPatient(id); 
        NotesClient patientNotes = notesClientService.lookupNotes(id); 

        int patientAge = AgeCalculator.calculateAge(patient.getDateOfBirth()); 
        int numTriggerTerms = RiskLevelAssessor.countNumTriggerTerms(patientNotes.getNotes());

        String riskLevel = RiskLevelAssessor.classifyDiabetesRiskLevel(patientAge, patient.getGender(), numTriggerTerms);

        return new RiskAssessment(id, patientNotes.getPatient(), riskLevel); 
    }

}
