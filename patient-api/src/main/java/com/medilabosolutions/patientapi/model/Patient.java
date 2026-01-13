package com.medilabosolutions.patientapi.model;

public class Patient {
    private String lastName; 
    private String firstName; 
    private String dateOfBirth; 
    private String gender; 
    private String address; 
    private String phone; 

    public Patient() {
        // In case we use Jackson 
    }

    public Patient(String lastName, String firstName, String dateOfBirth, String gender, String address, String phone) {
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

    public void setLastName(String lastName) {
        this.lastName = lastName; 
    }

    public String getFirstName() {
        return firstName; 
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; 
    }

    public String getDateOfBirth() {
        return dateOfBirth; 
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth; 
    }

    public String getGender() {
        return gender; 
    }

    public void setGender(String gender) {
        this.gender = gender; 
    }

    public String getAddress() {
        return address; 
    }

    public void setAddress(String address) {
        this.address = address; 
    }

    public String getPhone() {
        return phone; 
    }

    public void setPhone(String phone) {
        this.phone = phone; 
    }
}
