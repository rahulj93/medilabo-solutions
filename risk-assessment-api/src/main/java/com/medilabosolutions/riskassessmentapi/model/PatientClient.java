package com.medilabosolutions.riskassessmentapi.model; 

public class PatientClient {
    private Long id;

    private String lastName; 
    private String firstName; 
    private String dateOfBirth; 
    private String gender; 
    private String address; 
    private String phone; 

    public PatientClient() {
        // In case we use Jackson 
    }

    public PatientClient(String lastName, String firstName, String dateOfBirth, String gender, String address, String phone) {
        this.lastName = lastName;
        this.firstName = firstName; 
        this.dateOfBirth = dateOfBirth;
        this.gender = gender; 
        this.address = address;
        this.phone = phone;
    }

    public String getLastName() {
        return lastName; 
    }

    public String getFirstName() {
        return firstName; 
    }

    public String getDateOfBirth() {
        return dateOfBirth; 
    }

    public String getGender() {
        return gender; 
    }

    public String getAddress() {
        return address; 
    }

    public String getPhone() {
        return phone; 
    }

}
