package com.example.projekt.service;

import com.example.projekt.model.OrderedProduct;
import com.example.projekt.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderedProductService implements IOrderedProductService
{
    @Autowired
    OrderedProductRepository orderedProductRepository;

    @Override
    public List<OrderedProduct> findAll() {
        return (List<OrderedProduct>) orderedProductRepository.findAll();
    }
}
