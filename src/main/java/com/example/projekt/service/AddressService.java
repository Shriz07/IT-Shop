package com.example.projekt.service;
import com.example.projekt.model.Address;
import com.example.projekt.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService
{
    @Autowired
    private AddressRepository repository;

    @Override
    public List<Address> findAll() {
        return (List<Address>) repository.findAll();
    }
}
