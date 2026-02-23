package com.medilabosolutions.riskassessmentapi.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public static int calculateAge(String dateOfBirth) {
        return Period.between(
            LocalDate.parse(dateOfBirth, formatter), 
            LocalDate.now()
        ).getYears();
    }
}
