package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.CartItem;
import com.example.projekt.model.PaymentMethod;
import com.example.projekt.model.User;
import com.example.projekt.repository.PaymentMethodRepository;
import com.example.projekt.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController
{
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        User user = customUserDetails.getUser();
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) paymentMethodRepository.findAll();
        float total = cartItemService.getTotal(user);

        model.addAttribute("user", user);
        model.addAttribute("methods", paymentMethods);
        model.addAttribute("total", total);
        return "checkout";
    }
}
