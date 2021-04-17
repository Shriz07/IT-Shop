package com.example.projekt.repository;

import com.example.projekt.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Order, Integer>
{
}
