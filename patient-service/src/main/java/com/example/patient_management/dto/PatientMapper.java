package com.example.patient_management.dto;

import com.example.patient_management.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponceDTO toDto(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientResponceDTO patientDTO = new PatientResponceDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOFBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO requestDTO){
        Patient patient = new Patient();
        patient.setName(requestDTO.getName());;
        patient.setAddress(requestDTO.getAddress());
        patient.setEmail(requestDTO.getEmail());
        patient.setRegisteredDate(LocalDate.parse(requestDTO.getRegisteredDate()));
        patient.setDateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()));

        return patient;
    }
}
