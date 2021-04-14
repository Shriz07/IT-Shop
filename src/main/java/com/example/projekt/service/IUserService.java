package com.example.projekt.service;

import com.example.projekt.model.User;

import java.util.List;

public interface IUserService
{
    public List<User> findAll();

    public User findById(Integer id);

    public boolean changeRole(Integer id);
}
