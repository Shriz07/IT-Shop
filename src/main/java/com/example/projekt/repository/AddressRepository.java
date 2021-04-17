package com.example.projekt.repository;

import com.example.projekt.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>
{
    public Address findAddressByCityAndPostalCodeAndStreetAndHomeNumber(String city, String postalCode, String street, String homeNumber);
}
