package com.example.projekt.controller;

import com.example.projekt.details.CustomUserDetails;
import com.example.projekt.model.*;
import com.example.projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController
{
    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderedProductService orderedProductService;

    @GetMapping("/manageOrders")
    public String manageOrders(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        User user = customUserDetails.getUser();
        if(user == null)
            return "error";
        List<Order> orders;
        System.out.println(customUserDetails.getAuthorities());
        if(customUserDetails.getAuthorities().contains(new SimpleGrantedAuthority("admin")))
            orders = orderService.findAll();
        else
            orders = orderService.findByUser(user);

        List<OrderStatus> orderStatuses = orderStatusService.findAll();
        Integer val = -1;

        model.addAttribute("orders", orders);
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("val", val);
        return "manageOrders";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(Model model, @RequestParam(value = "orderId") Integer id, @RequestParam("orderStatusId") Integer orderStatusId)
    {
        orderService.updateOrderStatus(id, orderStatusId);

        List<Order> orders = orderService.findAll();
        List<OrderStatus> orderStatuses = orderStatusService.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("orderStatuses", orderStatuses);
        return "manageOrders";
    }

    @GetMapping("/orderDetails")
    public String orderDetails(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(value = "orderId") Integer id)
    {
        User user = customUserDetails.getUser();
        if(user == null)
            return "error";
        Order order = orderService.findById(id);
        if(customUserDetails.getAuthorities().contains(new SimpleGrantedAuthority("user")))
            if(order.getUser().getId() != user.getId())
                return "error";

        List<OrderedProduct> orderedProducts = orderedProductService.findByOrder(order);


        model.addAttribute("orderedProducts", orderedProducts);
        model.addAttribute("address", order.getAddress());
        model.addAttribute("user", order.getUser());
        model.addAttribute("totalCost", order.getPayment().getTotalAmount());
        return "orderDetails";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        User user = customUserDetails.getUser();
        Address address = user.getAddress();
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
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
        Address addr = addressService.addNewAddress(address);

        User user = customUserDetails.getUser();
        float total = cartItemService.getTotal(user);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);

        Payment payment = paymentService.addPayment(total, paymentMethod, true);

        OrderStatus orderStatus = orderStatusService.findById(1);

        Order order = orderService.addOrder(address, payment, user, orderStatus);

        List<CartItem> cartItems =  cartItemService.listCartItemsByUser(user);
        for(CartItem item : cartItems)
        {
            orderedProductService.addOrderedProduct(order, item.getProduct(), item.getQuantity());
            cartItemService.removeProduct(item.getProduct().getProductId(), user);
        }

        response.sendRedirect("/");
    }
}
