package com.example.projekt.service;

import com.example.projekt.model.Order;
import com.example.projekt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IOrderService
{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }
}
