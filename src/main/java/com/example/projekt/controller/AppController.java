package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.*;
import com.example.projekt.repository.AddressRepository;
import com.example.projekt.repository.ProductRepository;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
public class AppController
{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return homePage(model, 1);
    }

    @GetMapping("/page/{pageNo}")
    public String homePage(Model model, @PathVariable (value = "pageNo") int pageNo)
    {
        int pageSize = 9;

        Page<Product> page = productService.findProductsOnPage(pageNo, pageSize);
        List<Product> products = page.getContent();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "register";
    }

    @GetMapping("/contactUs")
    public String contactUs()
    {
        return "contactUs";
    }
}
