package com.example.projekt.service;

import com.example.projekt.model.Product;
import com.example.projekt.model.Specification;
import com.example.projekt.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService implements ISpecificationService
{
    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    public List<Specification> findAll() {
        return specificationRepository.findAll();
    }

    public void addSpecification(Specification specification)
    {
        specificationRepository.save(specification);
    }
}
