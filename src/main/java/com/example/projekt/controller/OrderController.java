package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.*;
import com.example.projekt.repository.*;
import com.example.projekt.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController
{
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        User user = customUserDetails.getUser();
        Address address = user.getAddress();
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) paymentMethodRepository.findAll();
        float total = cartItemService.getTotal(user);

        model.addAttribute("user", user);
        model.addAttribute("address", address);
        model.addAttribute("methods", paymentMethods);
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/checkout/placeorder")
    public void placeOrder(HttpServletResponse response, @AuthenticationPrincipal CustomUserDetails customUserDetails, Address address, @RequestParam(value = "paymentMethodId") int paymentMethodId) throws IOException
    {
        Address addr = addressRepository.findAddressByCityAndPostalCodeAndStreetAndHomeNumber(address.getCity(), address.getPostalCode(), address.getStreet(), address.getHomeNumber());
        if(addr != null)
            address = addr;
        else
            addressRepository.save(address);

        User user = customUserDetails.getUser();
        float total = cartItemService.getTotal(user);
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).get();

        Payment payment = new Payment();
        payment.setTotalAmount(total);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaid(true);
        paymentRepository.save(payment);

        OrderStatus orderStatus = orderStatusRepository.findById(1).get();

        Order order = new Order();
        order.setAddress(address);
        order.setPayment(payment);
        order.setUser(user);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        List<CartItem> cartItems =  cartItemService.listCartItemsByUser(user);
        for(CartItem item : cartItems)
        {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setOrder(order);
            orderedProduct.setProduct(item.getProduct());
            orderedProduct.setQuantity(item.getQuantity());
            orderedProductRepository.save(orderedProduct);

            cartItemService.removeProduct(item.getProduct().getProductId(), user);
        }

        response.sendRedirect("/");
    }
}
