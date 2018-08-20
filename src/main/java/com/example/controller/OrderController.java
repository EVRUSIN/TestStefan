package com.example.controller;

import com.example.model.Order;
import com.example.model.enums.Status;
import com.example.repository.ClientRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/all")
    public Iterable<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public Iterable<Order> findAllClientOrders(@PathVariable Long clientId) {
        return orderRepository.findByOwner(clientRepository.findById(clientId).get());
    }

    @PostMapping("/{clientId}")
    public Order createOrder(@PathVariable Long clientId, @RequestBody Order order){
        order.setOwner(clientRepository.findById(clientId).get());
        order.setStatus(Status.CREATED);
        orderRepository.save(order);
        return order;
    }

    @PatchMapping("/{orderId}/confirm")
    public Order updateStatus(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(Status.CONFIRMED);
        orderRepository.save(order);
        return order;
    }


}
