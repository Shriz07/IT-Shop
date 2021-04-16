package com.example.projekt.restController;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.User;
import com.example.projekt.service.CartItemService;
import com.example.projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestController
{
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Integer productId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        if(customUserDetails == null)
            return "You must login to add this product to cart.";
        User user = customUserDetails.getUser();

        Integer addedQuantity = cartItemService.addProduct(productId, quantity, user);

        return addedQuantity + " item(s) were added";
    }

    @PostMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Integer productId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        if(customUserDetails == null)
            return "Please log in";
        User user = customUserDetails.getUser();

        float subtotal = cartItemService.updateQuantity(productId, quantity, user);

        return String.valueOf(subtotal);
    }

    @PostMapping("/cart/remove/{pid}")
    public String removeProductFromCart(@PathVariable("pid") Integer productId, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        if(customUserDetails == null)
            return "Please log in";
        User user = customUserDetails.getUser();

        cartItemService.removeProduct(productId, user);

        return "The product has been removed";
    }
}
