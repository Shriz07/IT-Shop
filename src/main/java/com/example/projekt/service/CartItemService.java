package com.example.projekt.service;

import com.example.projekt.model.CartItem;
import com.example.projekt.model.User;
import com.example.projekt.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService
{
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> listCartItems(User user)
    {
        return cartItemRepository.findByUser(user);
    }
}
