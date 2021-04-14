package com.example.projekt.service;
import com.example.projekt.model.Product;
import com.example.projekt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService
{
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public List<Product> findAllInCategory(Integer id) {
        return repository.findAllInCategory(id);
    }
}
