package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.Address;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;


    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String password, @RequestParam String phoneNumber)
    {
        User newUser = new User(name, surname, email, password, phoneNumber);
        userRepository.save(newUser);
        return "User added";
    }

    @GetMapping("/manageUsers")
    public String findUsers(Model model)
    {
        List<User> users = (List<User>) userService.findAll();
        model.addAttribute("users", users);

        return "manageUsers";
    }

    @PostMapping("/changeRole")
    public String changeRole(Model model, @RequestParam Integer id)
    {
        userService.changeRole(id);
        List<User> users = (List<User>) userService.findAll();
        model.addAttribute("users", users);
        return "manageUsers";
    }

    @GetMapping("/myAccount")
    public String myAccount(Model model)
    {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        return "myAccount";
    }
}
