package com.example.projekt.service;

import com.example.projekt.model.Product;

import java.util.List;

public interface IProductService
{
    public List<Product> findAll();

    public List<Product> findAllInCategory(Integer id);
}
