package com.example.projekt.repository;

import com.example.projekt.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>
{
    @Query("SELECT o FROM OrderStatus o WHERE o.statusId = ?1")
    Optional<OrderStatus> findById(Integer id);
}
