package com.example.patient_management.repo;


import com.example.patient_management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@org.springframework.stereotype.Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
