package com.example.projekt.repository;

import com.example.projekt.model.Order;
import com.example.projekt.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer>
{
    public List<OrderedProduct> findByOrder(Order order);
}
