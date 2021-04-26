package com.example.projekt.service;

import com.example.projekt.model.Order;
import com.example.projekt.model.OrderedProduct;
import com.example.projekt.model.User;
import com.example.projekt.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductService implements IOrderedProductService
{
    @Autowired
    OrderedProductRepository orderedProductRepository;

    @Override
    public List<OrderedProduct> findAll() {
        return (List<OrderedProduct>) orderedProductRepository.findAll();
    }

    public List<OrderedProduct> findByOrder(Order order)
    {
        return orderedProductRepository.findByOrder(order);
    }
}
