package com.example.projekt.repository;

import com.example.projekt.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Integer>
{
}
