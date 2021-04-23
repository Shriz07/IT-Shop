package com.example.projekt.service;

import com.example.projekt.model.OrderStatus;

import java.util.List;

public interface IOrderStatusService {
    public List<OrderStatus> findAll();
}
