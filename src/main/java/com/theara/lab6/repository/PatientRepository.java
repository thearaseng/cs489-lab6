package com.theara.lab6.repository;

import com.theara.lab6.model.Dentist;
import com.theara.lab6.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findByPatientId(String patientId);
    List<Patient> findByPatientIdContainingIgnoreCaseOrNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(String patientId, String name, String phone, String email);
}
