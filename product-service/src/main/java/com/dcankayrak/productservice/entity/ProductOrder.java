package com.dcankayrak.productservice.entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "product_orders")
public class ProductOrder {

    private Product product;
    private Order order;
}
