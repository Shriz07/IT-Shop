package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.Address;
import com.example.projekt.model.User;
import com.example.projekt.repository.AddressRepository;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.AddressService;
import com.example.projekt.service.IUserService;
import com.example.projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/registerUser")
    public String registerUser(Model model, User user, Address address)
    {
        if( userService.findByEmail(user.getEmail()) != null)
        {
            model.addAttribute("user", new User());
            model.addAttribute("address", new Address());
            model.addAttribute("status", "User with that email exists.");
            return "register";
        }
        Address addr = addressService.addNewAddress(address);
        userService.addNewUser(user, address.getAddressId());
        return "registerSuccess";
    }

    @GetMapping("/manageUsers")
    public String findUsers(Model model)
    {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "manageUsers";
    }

    @PostMapping("/changeRole")
    public String changeRole(Model model, @RequestParam Integer id)
    {
        userService.changeRole(id);
        List<User> users = userService.findAll();
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
