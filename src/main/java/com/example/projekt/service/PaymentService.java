package com.example.projekt.service;

import com.example.projekt.model.Payment;
import com.example.projekt.model.PaymentMethod;
import com.example.projekt.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService
{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return (List<Payment>) paymentRepository.findAll();
    }

    public Payment addPayment(float total, PaymentMethod paymentMethod, boolean isPaid)
    {
        Payment payment = new Payment();
        payment.setTotalAmount(total);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaid(true);
        paymentRepository.save(payment);
        return payment;
    }
}
