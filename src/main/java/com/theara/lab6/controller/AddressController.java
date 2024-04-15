package com.theara.lab6.controller;

import com.theara.lab6.model.Address;
import com.theara.lab6.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public List<Address> getAllAddresses() {

        // Fetch all addresses from the repository
        List<Address> addresses = (List<Address>) addressService.findAll();

        // Sort addresses by city in ascending order
        addresses.sort(Comparator.comparing(a -> a.getAddress().toLowerCase()));

        return addresses;
    }

}
