package com.theara.lab6.repository;

import com.theara.lab6.model.Address;
import com.theara.lab6.model.Dentist;
import org.springframework.data.repository.CrudRepository;

public interface DentistRepository extends CrudRepository<Dentist, Integer> {

}
