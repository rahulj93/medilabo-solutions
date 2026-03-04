package com.medilabosolutions.riskassessmentapi.util;

import java.util.List;
import java.util.stream.Collectors;

public class RiskLevelAssessor {

    private static final List<String> diabetesTriggerTerms = List.of(
        "Hemoglobin A1C",
        "Microalbumin",
        "Height",
        "Weight",
        "Smoking",
        "Abnormal",
        "Cholesterol",
        "Dizziness",
        "Relapse",
        "Reaction",
        "Antibody"
    );

    // Precompute lowercase for efficiency
    private static final List<String> diabetesTriggerTermsLower = diabetesTriggerTerms.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

    public static int countNumTriggerTerms(List<String> notes) {
        int numTriggerTerms = 0; 
        for (String note : notes) {
            String lowerNote = note.toLowerCase(); 

            // for (String trigger : triggerTerms) {
            //     if (lowerNote.contains(trigger.toLowerCase())) {
            //         numTriggerTerms += 1; 
            //     }
            // }

            for (String trigger : diabetesTriggerTermsLower) {
                if (lowerNote.contains(trigger)) {
                    numTriggerTerms += 1; 
                }
            }
        }
        return numTriggerTerms; 
    }

    public static String classifyDiabetesRiskLevel(int age, String gender, int numTriggerTerms) {
        System.out.println(age);
        System.out.println(gender);
        System.out.println(numTriggerTerms);

        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Gender is missing for patient");
        }

        String defaultRiskLevel = "None"; 
        if (numTriggerTerms <= 1) return defaultRiskLevel;

        // Borderline 
        if (age > 30 && 2 <= numTriggerTerms && numTriggerTerms <= 5) return "Borderline";               

        // In Danger
        if (age > 30 && (numTriggerTerms == 6 || numTriggerTerms == 7)) return "In Danger";    
        if (age < 30) {
            if ((gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) 
                && (numTriggerTerms == 3|| numTriggerTerms == 4)) 
                return "In Danger";
            if ((gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) 
                && (numTriggerTerms == 4|| numTriggerTerms == 5)) 
                return "In Danger";
        }

        // Early Onset 
        if (age > 30 && numTriggerTerms >= 8) return "Early Onset"; 
        if (age < 30) {
            if ((gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) 
                && (numTriggerTerms > 5)) 
                return "Early Onset";
            if ((gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) 
                && (numTriggerTerms > 6)) 
                return "Early Onset";
        }

        return defaultRiskLevel; 

        // if (age > 30) {
        //     if (2 <= numTriggerTerms && numTriggerTerms <= 5) return "Borderline";               
        //     if ((numTriggerTerms == 6 || numTriggerTerms == 7)) return "In Danger";            
        //     if (numTriggerTerms >= 8) return "Early Onset";            
        // } else {
        //     if (gender.equals("male") ) {
        //         if (numTriggerTerms == 3|| numTriggerTerms == 4) return "In Danger"; 
        //         if (numTriggerTerms > 5) return "Early Onset"; 
        //     }
        //     if (gender.equals("female") ) {
        //         if (numTriggerTerms == 4|| numTriggerTerms == 5) return "In Danger"; 
        //         if (numTriggerTerms > 6) return "Early Onset"; 
        //     }
        // }        

        // // Borderline 
        // if (age > 30 && 2 <= numTriggerTerms && numTriggerTerms <= 5) return "Borderline";               

        // // In Danger
        // if (age > 30 && (numTriggerTerms == 6 || numTriggerTerms == 7)) return "In Danger";    
        // if (age < 30 
        //     && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) 
        //     && (numTriggerTerms == 3|| numTriggerTerms == 4)) 
        //     return "In Danger";
        // if (age < 30 
        //     && (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) 
        //     && (numTriggerTerms == 4|| numTriggerTerms == 5)) 
        //     return "In Danger";

        // // Early Onset 
        // if (age > 30 && numTriggerTerms >= 8) return "Early Onset";            
        // if (age < 30 
        //     && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) 
        //     && (numTriggerTerms > 5)) 
        //     return "Early Onset";
        // if (age < 30 
        //     && (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) 
        //     && (numTriggerTerms > 6)) 
        //     return "Early Onset";
        
        // return defaultRiskLevel; 
    }

}
