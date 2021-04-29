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
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return (List<Brand>) brandRepository.findAll();
    }

    public int addBrand(Brand brand)
    {
        try
        {
            brandRepository.save(brand);
        }
        catch (Exception e)
        {
            return 0;
        }
        return 1;
    }
}
