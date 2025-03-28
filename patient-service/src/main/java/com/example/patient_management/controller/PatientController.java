package com.example.patient_management.controller;


import com.example.patient_management.dto.PatientRequestDTO;
import com.example.patient_management.dto.PatientResponceDTO;
import com.example.patient_management.dto.validaters.CreatePatientValidationGroup;
import com.example.patient_management.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponceDTO>> getPatients(){
        List<PatientResponceDTO> patientResponceDTOS = service.GetPatients();
        return ResponseEntity.ok().body(patientResponceDTOS);
    }

    @PostMapping
    public ResponseEntity<PatientResponceDTO> createPatient(
        @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponceDTO patientResponceDTO = service.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponceDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponceDTO patientResponceDTO = service.updatePatient(id,patientRequestDTO);

        return ResponseEntity.ok().body(patientResponceDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientResponceDTO> getByID(@PathVariable UUID id){
        PatientResponceDTO patientResponceDTO = service.getById(id);

        return ResponseEntity.ok().body(patientResponceDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        service.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
