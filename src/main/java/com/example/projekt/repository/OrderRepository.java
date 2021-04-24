package com.example.projekt.repository;

import com.example.projekt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
    @Query("UPDATE Order o SET o.orderStatus.statusId = ?2 WHERE o.orderId = ?1")
    @Modifying
    public void updateOrderStatus(Integer orderId, Integer orderStatusId);
}
