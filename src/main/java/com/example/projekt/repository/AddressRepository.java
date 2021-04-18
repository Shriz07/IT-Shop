package com.example.projekt.repository;

import com.example.projekt.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
    public Address findAddressByCityAndPostalCodeAndStreetAndHomeNumber(String city, String postalCode, String street, String homeNumber);
}
