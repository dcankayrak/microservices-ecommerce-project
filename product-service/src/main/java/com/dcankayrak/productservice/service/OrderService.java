package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.entity.Order;
import com.dcankayrak.productservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        this.orderRepository.save(order);
        return order;
    }
}
