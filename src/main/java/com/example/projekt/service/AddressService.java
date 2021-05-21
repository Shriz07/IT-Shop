package com.example.projekt.service;
import com.example.projekt.model.Address;
import com.example.projekt.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService
{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    public Address addNewAddress(Address address)
    {
        Address addr = addressRepository.findAddressByCityAndPostalCodeAndStreetAndHomeNumber(address.getCity(), address.getPostalCode(), address.getStreet(), address.getHomeNumber());
        if(addr != null)
            address = addr;
        else
            addressRepository.save(address);
        return address;
    }

    public void updateAddress(Address address)
    {
        addressRepository.updateAddress(address.getAddressId(), address.getCity(), address.getPostalCode(), address.getStreet(), address.getHomeNumber());
    }
}
