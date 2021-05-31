package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.Address;
import com.example.projekt.model.User;
import com.example.projekt.service.AddressService;
import com.example.projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController
{
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService, AddressService addressService)
    {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/registerUser")
    public String registerUser(Model model, User user, Address address)
    {
        if( userService.findByEmail(user.getEmail()) != null)
        {
            user.setEmail("");
            model.addAttribute("user", user);
            model.addAttribute("address", address);
            model.addAttribute("status", "User with that email exists.");
            return "register";
        }
        Address addr = addressService.addNewAddress(address);
        if(!userService.addNewUser(user, addr.getAddressId()))
            return "registerFailed";
        return "registerSuccess";
    }

    @GetMapping("/registerUser")
    public void registerUser(HttpServletResponse response) throws IOException
    {
        response.sendRedirect("/register");
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "register";
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
        model.addAttribute("addr", user.getAddress());
        return "myAccount";
    }

    @PostMapping("/updateUserAccount")
    public String updateUserAccount(Model model, Address addr, User user)
    {
        addressService.updateAddress(addr);
        model.addAttribute("user", user);
        model.addAttribute("addr", addr);
        return "myAccount";
    }
}
