package com.example.projekt.details;

import com.example.projekt.model.Address;
import com.example.projekt.model.User;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails
{
    private User user;

    public CustomUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {return user; }

    public Integer getId() { return user.getId(); }

    public String getFullName()
    {
        return user.getName() + " " + user.getSurname();
    }

    public String getName() { return user.getName(); }

    public String getSurname() { return user.getSurname(); }

    public String getPhoneNumber() {return user.getPhoneNumber(); }

    public String getEmail() { return user.getEmail(); }

    public Address getAddress() { return user.getAddress();}
}
