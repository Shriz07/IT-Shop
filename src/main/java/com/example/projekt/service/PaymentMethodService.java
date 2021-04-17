package com.example.projekt.service;

import com.example.projekt.model.PaymentMethod;
import com.example.projekt.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentMethodService implements IPaymentMethodService
{
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> findAll() {
        return (List<PaymentMethod>) paymentMethodRepository.findAll();
    }
}
