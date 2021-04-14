package com.example.projekt.controller;

import com.example.projekt.model.Brand;
import com.example.projekt.model.Category;
import com.example.projekt.model.Product;
import com.example.projekt.model.User;
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
        return "register";
    }

    @GetMapping("/testindex")
    public String  testIndex(Model model)
    {
        return "testindex";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        userRepository.save(user);
        return "registerSuccess";
    }

    @GetMapping("/contactUs")
    public String contactUs()
    {
        return "contactUs";
    }
}
