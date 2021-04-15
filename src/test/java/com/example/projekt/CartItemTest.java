package com.example.projekt;

import com.example.projekt.model.CartItem;
import com.example.projekt.model.Product;
import com.example.projekt.model.User;
import com.example.projekt.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartItemTest
{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem()
    {
        Product product = entityManager.find(Product.class, 31);
        User user = entityManager.find(User.class, 14);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(1);

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        assertTrue(savedCartItem.getCartid() > 0);
    }
}
