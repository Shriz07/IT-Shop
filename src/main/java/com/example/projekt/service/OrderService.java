package com.example.projekt.service;

import com.example.projekt.model.*;
import com.example.projekt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService
{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> findByUser(User user) { return orderRepository.findByUser(user); }

    public Order findById(Integer id) { return  orderRepository.findById(id).get(); }

    public Order addOrder(Address address, Payment payment, User user, OrderStatus orderStatus)
    {
        Order order = new Order();
        order.setAddress(address);
        order.setPayment(payment);
        order.setUser(user);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return order;
    }

    public void updateOrderStatus(Integer orderId, Integer orderStatusId)
    {
        orderRepository.updateOrderStatus(orderId, orderStatusId);
    }
}
