package com.dcankayrak.productservice.repository;

import com.dcankayrak.productservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
