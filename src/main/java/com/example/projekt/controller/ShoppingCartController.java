package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.CartItem;
import com.example.projekt.model.User;
import com.example.projekt.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController
{
    private final CartItemService cartItemService;

    @Autowired
    public ShoppingCartController(CartItemService cartItemService)
    {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/shoppingCart")
    public String cart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        User user = customUserDetails.getUser();
        List<CartItem> cartItems =  cartItemService.listCartItemsByUser(user);
        model.addAttribute("cartItems", cartItems);

        return "cart";
    }
}
