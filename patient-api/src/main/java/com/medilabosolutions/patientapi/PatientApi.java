package com.medilabosolutions.patientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PatientApi {
    @RequestMapping("/")
    String rootClass() {
        return "Patient API!"; 
    }

    public static void main(String[] args) {
        SpringApplication.run(PatientApi.class, args); 
    }
}