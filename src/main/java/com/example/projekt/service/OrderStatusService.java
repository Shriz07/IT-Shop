package com.example.projekt.service;

import com.example.projekt.model.OrderStatus;
import com.example.projekt.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderStatusService implements IOrderStatusService
{
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() {
        return (List<OrderStatus>) orderStatusRepository.findAll();
    }
}
