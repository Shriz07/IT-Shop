package com.example.projekt.controller;

import com.example.projekt.model.Specification;
import com.example.projekt.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SpecificationController
{
    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService)
    {
        this.specificationService = specificationService;
    }

    @GetMapping("/addSpecification")
    public String addSpecification(Model model, @RequestParam(value = "productId") Integer productId)
    {
        Specification specification = new Specification();
        model.addAttribute("specification", specification);
        model.addAttribute("productId", productId);
        return "addSpecification";
    }

    @PostMapping("/addSpecification")
    public String addSpecification(Model model, Specification specification, @RequestParam(value = "productId") Integer productId)
    {
        specification.setProductId(productId);
        specificationService.addSpecification(specification);
        model.addAttribute("status", "Specification added");
        model.addAttribute("specification", new Specification());
        model.addAttribute("productId", productId);
        return "addSpecification";
    }
}
