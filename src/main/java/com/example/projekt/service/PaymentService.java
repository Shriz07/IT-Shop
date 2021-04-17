package com.example.projekt.service;

import com.example.projekt.model.Payment;
import com.example.projekt.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentService implements IPaymentService
{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return (List<Payment>) paymentRepository.findAll();
    }
}
