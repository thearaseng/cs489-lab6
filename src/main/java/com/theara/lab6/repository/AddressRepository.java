package com.theara.lab6.repository;

import com.theara.lab6.model.Address;
import com.theara.lab6.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
