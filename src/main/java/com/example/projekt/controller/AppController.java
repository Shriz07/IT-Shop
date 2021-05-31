package com.example.projekt.controller;

import com.example.projekt.model.*;
import com.example.projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController
{
    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public AppController(ProductService productService, CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
    }

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

    @GetMapping("/contactUs")
    public String contactUs()
    {
        return "contactUs";
    }
}
