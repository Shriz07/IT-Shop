package com.example.projekt.service;

import com.example.projekt.model.OrderStatus;
import com.example.projekt.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService implements IOrderStatusService
{
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    public OrderStatus findById(Integer id) { return orderStatusRepository.findById(id).get(); }
}
