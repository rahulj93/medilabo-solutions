package com.medilabosolutions.riskassessmentapi.model;

public class RiskAssessment {

    String id; //this is the patient id 
    String patientName; 
    String riskLevel; 
    
    public RiskAssessment(String id, String patientName, String riskLevel) {
        this.id = id; 
        this.patientName = patientName; 
        this.riskLevel = riskLevel; 
    }

    public String getId() {
        return this.id; 
    }

    public void setId(String id) {
        this.id = id; 
    }

    public String getPatientName() {
        return this.patientName; 
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName; 
    }

    public String getRiskLevel() {
        return this.riskLevel; 
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel; 
    }
}
