package com.example.projekt.model;

import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer addressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", insertable = false, updatable = false, nullable = false)
    private Address address;

    public User()
    {}

    public User(String name, String surname, String email, String password, String phoneNumber)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(Integer id, String name, String surname, String email, String phoneNumber, String password)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Integer getId() { return id; }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role) { this.role = role; };

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail() {return email;}

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() { return password; }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getAddressId() { return addressId; }

    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public Address getAddress() { return address; }
}
