package com.example.projekt.repository;

import com.example.projekt.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
    public Address findAddressByCityAndPostalCodeAndStreetAndHomeNumber(String city, String postalCode, String street, String homeNumber);

    @Query("UPDATE Address a SET a.city = ?2, a.postalCode = ?3, a.street = ?4, a.homeNumber = ?5  WHERE a.addressId= ?1")
    @Modifying
    public void updateAddress(Integer addressId, String city, String postalCode, String Street, String homeNumber);
}
