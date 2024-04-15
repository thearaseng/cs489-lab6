package com.theara.lab6.controller;

import com.theara.lab6.dto.PatientDTO;
import com.theara.lab6.model.Address;
import com.theara.lab6.model.Patient;
import com.theara.lab6.service.AddressService;
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
    @Autowired
    private AddressService addressService;

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
    public ResponseEntity<Patient> registerPatient(@RequestBody PatientDTO patientDTO) {
        // Create a new Patient object from the DTO
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setName(patientDTO.getName());
        patient.setPhone(patientDTO.getPhone());
        patient.setEmail(patientDTO.getEmail());

        // Set the primary address for the patient
        Address primaryAddress = new Address();
        primaryAddress.setAddress(patientDTO.getAddress().getAddress());

        addressService.save(primaryAddress);

        patient.setAddress(primaryAddress);

        // Save the patient to the database
        patientService.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @RequestBody PatientDTO updatedPatientDTO) {
        // Retrieve the patient by id
        Patient existingPatient = patientService.findById(id);

        // Update the patient data
        existingPatient.setName(updatedPatientDTO.getName());
        existingPatient.setPhone(updatedPatientDTO.getPhone());
        existingPatient.setEmail(updatedPatientDTO.getEmail());

        // Update the primary address
        Address primaryAddress = existingPatient.getAddress();
        primaryAddress.setAddress(updatedPatientDTO.getAddress().getAddress());
        addressService.save(primaryAddress);

        existingPatient.setAddress(primaryAddress);

        // Save the updated patient data to the database
        patientService.save(existingPatient);

        return new ResponseEntity<>(existingPatient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        // Retrieve the patient by id
        Patient patientToDelete = patientService.findById(id);

        // Delete the patient from the database
        patientService.delete(patientToDelete);

        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    public List<Patient> searchPatients(@PathVariable("searchString") String searchString) {
        // Query patients whose data matches the input searchString
        return patientService.findByPatientIdContainingIgnoreCaseOrNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(searchString);
    }

    private List<Patient> iterableToList(Iterable<Patient> iterable) {
        List<Patient> list = new ArrayList<>();
        for (Patient item : iterable) {
            list.add(item);
        }
        return list;
    }

}
