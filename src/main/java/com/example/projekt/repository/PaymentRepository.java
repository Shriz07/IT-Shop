package com.example.projekt.repository;

import com.example.projekt.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer>
{
}
