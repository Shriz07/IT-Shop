package com.example.projekt.service;

import com.example.projekt.model.CartItem;
import com.example.projekt.model.Product;
import com.example.projekt.model.User;
import com.example.projekt.repository.CartItemRepository;
import com.example.projekt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartItemService
{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> listCartItemsByUser(User user)
    {
        return cartItemRepository.findByUser(user);
    }

    public long countItemsInCart(User user) { return cartItemRepository.countItemsInCart(user.getId()); }

    public Integer addProduct(Integer productId, Integer quantity, User user)
    {
        Integer addedQuantity = quantity;
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if(cartItem != null)
        {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }
        else
        {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }

        cartItemRepository.save(cartItem);

        return addedQuantity;
    }

    public float updateQuantity(Integer productId, Integer quantity, User user)
    {
        cartItemRepository.updateQuantity(quantity, productId, user.getId());
        Product product = productRepository.findById(productId).get();
        float subtotal = product.getPrice() * quantity;
        subtotal = (float) (Math.round(subtotal * 100.0) / 100.0);
        return subtotal;
    }

    public float getTotal(User user)
    {
        float total = 0;
        List<CartItem> cartItems = listCartItemsByUser(user);
        for(CartItem cartItem : cartItems)
            total = total + cartItem.getProduct().getPrice() * cartItem.getQuantity();
        total = (float) (Math.round(total * 100.0) / 100.0);
        return total;
    }

    public void removeProduct(Integer productId, User user)
    {
        cartItemRepository.deleteByUserAndProduct(user.getId(), productId);
    }
}
