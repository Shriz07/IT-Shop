package com.example.projekt.service;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findById(Integer id) {
        User u = repository.findById(id);
        return u;
    }

    @Override
    public boolean changeRole(Integer id) {
        User u = findById(id);
        if(u == null)
            return false;
        if(u.getRole().equals("user"))
            u.setRole("admin");
        else
            u.setRole("user");
        repository.save(u);
        System.out.println("ROLE " + u.getRole());
        return true;
    }
}
