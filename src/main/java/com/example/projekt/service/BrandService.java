package com.example.projekt.service;
import com.example.projekt.model.Brand;
import com.example.projekt.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService
{
    @Autowired
    private BrandRepository repository;

    @Override
    public List<Brand> findAll() {
        return (List<Brand>) repository.findAll();
    }
}
