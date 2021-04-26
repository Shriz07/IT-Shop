package com.example.projekt.repository;

import com.example.projekt.model.CartItem;
import com.example.projekt.model.Product;
import com.example.projekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>
{
    List<CartItem> findByUser(User user);

    CartItem findByUserAndProduct(User user, Product product);

    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.productId = ?2 AND c.user.id = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Integer productId, Integer userId);

    @Query("DELETE FROM CartItem c WHERE c.user.id = ?1 AND c.product.productId = ?2")
    @Modifying
    void deleteByUserAndProduct(Integer userId, Integer productId);

    @Query("SELECT COUNT(c) FROM CartItem  c WHERE c.user.id = ?1")
    long countItemsInCart(Integer userId);
}
