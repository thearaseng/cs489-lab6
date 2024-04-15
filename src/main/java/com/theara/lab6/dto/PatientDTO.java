package com.theara.lab6.dto;

import com.theara.lab6.model.Address;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO {

    private Integer id;
    private String patientId;
    private String name;
    private String phone;
    private String email;

    private Address address;

}
