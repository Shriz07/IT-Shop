package com.example.projekt.controller;

import com.example.projekt.model.Category;
import com.example.projekt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/addCategory")
    public String addCategory(Model model)
    {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categories);
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(Model model, Category category)
    {
        if(categoryService.addCategory(category) == 0)
        {
            String status = "Category already exists";
            model.addAttribute("status", status);
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "addCategory";
    }
}
