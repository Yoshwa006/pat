package com.example.patient_management.service;

import com.example.patient_management.dto.PatientRequestDTO;
import com.example.patient_management.dto.PatientResponceDTO;
import com.example.patient_management.dto.PatientMapper;
import com.example.patient_management.exception.EmailAlreadyExistsException;
import com.example.patient_management.exception.PatientNotFoundException;
import com.example.patient_management.model.Patient;
import com.example.patient_management.repo.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<PatientResponceDTO> GetPatients() {
        List<Patient> patients = repo.findAll();

        return patients.stream()

                .map(PatientMapper::toDto)
                .toList();
    }


    public PatientResponceDTO createPatient(PatientRequestDTO patientRequestDTO) {


        if (repo.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A person with this email already exists !!" + patientRequestDTO.getEmail());
        }

        Patient newPatient = repo.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDto(newPatient);
    }

    public PatientResponceDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = repo.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found in database: " + id));

        if (repo.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDTO.getEmail());
        }

        // Updating patient details
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = repo.save(patient);


        return PatientMapper.toDto(updatedPatient);
    }
public PatientResponceDTO getById(UUID id){
        Patient patient = repo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found in database: " + id));
    return PatientMapper.toDto(patient);
}

public void deletePatient(UUID id){
        repo.deleteById(id);
}

}

