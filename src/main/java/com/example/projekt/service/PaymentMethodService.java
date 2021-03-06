package com.example.projekt.service;

import com.example.projekt.model.PaymentMethod;
import com.example.projekt.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService implements IPaymentMethodService
{
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> findAll() {
        return (List<PaymentMethod>) paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(Integer id) { return paymentMethodRepository.findById(id).get(); }
}
