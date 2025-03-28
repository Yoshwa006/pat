package com.example.patient_management.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String s){
        super(s);
    }
}
