package com.example.projekt.controller;

import com.example.projekt.model.*;
import com.example.projekt.repository.AddressRepository;
import com.example.projekt.repository.ProductRepository;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("")
    public String homePage(Model model)
    {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user, Address address)
    {
        //Dodac sprawdzenie czy adres juz nie istnieje
        addressRepository.save(address);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAddressId(address.getAddressId());
        userRepository.save(user);
        return "registerSuccess";
    }

    @GetMapping("/contactUs")
    public String contactUs()
    {
        return "contactUs";
    }
}
