package com.example.projekt.controller;

import com.example.projekt.model.Brand;
import com.example.projekt.repository.BrandRepository;
import com.example.projekt.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;


@Controller
public class BrandController
{
    @Autowired
    private IBrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/addBrand")
    public String addBrand(Model model)
    {
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        model.addAttribute("brand", new Brand());
        return "addBrand";
    }

    @PostMapping("/addBrand")
    public String addBrand(Model model, Brand brand)
    {
        try
        {
            brandRepository.save(brand);
        }
        catch (Exception e)
        {
            String status = "Brand already exists";
            model.addAttribute("status", status);
        }
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        return "addBrand";
    }
}
