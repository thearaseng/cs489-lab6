package com.theara.lab6.repository;

import com.theara.lab6.model.Patient;
import com.theara.lab6.model.Surgery;
import org.springframework.data.repository.CrudRepository;

public interface SurgeryRepository extends CrudRepository<Surgery, Integer> {

}
