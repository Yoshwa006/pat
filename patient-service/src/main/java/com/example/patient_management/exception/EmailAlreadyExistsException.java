package com.example.patient_management.exception;

public class EmailAlreadyExistsException extends RuntimeException{
public EmailAlreadyExistsException(String message){
    super(message);
}
}
