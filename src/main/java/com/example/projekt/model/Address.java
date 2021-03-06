package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer addressId;
    @Size(min = 3, max = 45)
    private String city;
    @Size(min = 5, max = 45)
    private String postalCode;
    @Size(min = 3, max = 45)
    private String street;
    @Size(min = 1, max = 10)
    private String homeNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address")
    private Set<User> users = new HashSet<>();

    public Address()
    {}

    public Address(String city, String postalCode, String street, String homeNumber)
    {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public Integer getAddressId() { return addressId; }

    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getHomeNumber() { return homeNumber; }

    public void setHomeNumber(String homeNumber) { this.homeNumber = homeNumber; }
}
