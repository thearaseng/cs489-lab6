package com.theara.lab6.controller;

import com.theara.lab6.dto.PatientDTO;
import com.theara.lab6.model.Address;
import com.theara.lab6.model.Patient;
import com.theara.lab6.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public List<Patient> getAllPatients() {
        List<Patient> patients = iterableToList(patientService.findAll());

        // Sort patients by last name
        patients.sort(Comparator.comparing(Patient::getName));

        return patients;
    }

    @GetMapping("/get/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") Integer id) {
        Patient patient = patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PatientDTO patientDTO) {
        // Create a new Patient object from the DTO
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setName(patientDTO.getName());
        patient.setPhone(patientDTO.getPhone());
        patient.setEmail(patientDTO.getEmail());
        // Set the primary address for the patient
        Address primaryAddress = new Address();
        primaryAddress.setAddress(patientDTO.getAddress().getAddress());
        patient.setAddress(primaryAddress);

        // Save the patient to the database
        patientService.save(patient);

        return new ResponseEntity<>("Patient registered successfully", HttpStatus.CREATED);
    }

    private List<Patient> iterableToList(Iterable<Patient> iterable) {
        List<Patient> list = new ArrayList<>();
        for (Patient item : iterable) {
            list.add(item);
        }
        return list;
    }

}
