package com.example.projekt.service;

import com.example.projekt.model.Payment;

import java.util.List;

public interface IPaymentService
{
    public List<Payment> findAll();
}
