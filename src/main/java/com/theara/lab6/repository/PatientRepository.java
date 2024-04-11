package com.theara.lab6.repository;

import com.theara.lab6.model.Dentist;
import com.theara.lab6.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
