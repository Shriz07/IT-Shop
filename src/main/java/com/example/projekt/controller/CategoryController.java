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
        int status = categoryService.addCategory(category);
        if(status != 0)
        {
            String message = "";
            switch (status)
            {
                case 1:
                    message = "Category name is too short";
                    break;
                case 2:
                    message = "Category already exists";
                    break;
                default:
                    message = "An error occurred";
                    break;
            }
            model.addAttribute("status", message);
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "addCategory";
    }
}
