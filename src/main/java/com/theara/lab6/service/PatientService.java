package com.theara.lab6.service;

import com.theara.lab6.model.Patient;
import com.theara.lab6.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

}
