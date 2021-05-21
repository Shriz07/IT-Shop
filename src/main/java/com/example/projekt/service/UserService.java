package com.example.projekt.service;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public boolean changeRole(Integer id) {
        User u = findById(id);
        if(u == null)
            return false;
        if(u.getRole().equals("user"))
            u.setRole("admin");
        else
            u.setRole("user");
        userRepository.save(u);
        return true;
    }

    public int addNewUser(User user, Integer addressId)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAddressId(addressId);
        userRepository.save(user);

        return 0;
    }

    public void updateUser(User user)
    {
        userRepository.save(user);
    }
}
