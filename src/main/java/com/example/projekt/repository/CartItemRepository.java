package com.example.projekt.repository;

import com.example.projekt.model.CartItem;
import com.example.projekt.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Integer>
{
    public List<CartItem> findByUser(User user);
}
