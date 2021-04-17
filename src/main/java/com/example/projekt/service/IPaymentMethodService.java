package com.example.projekt.service;

import com.example.projekt.model.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService
{
    public List<PaymentMethod> findAll();
}
