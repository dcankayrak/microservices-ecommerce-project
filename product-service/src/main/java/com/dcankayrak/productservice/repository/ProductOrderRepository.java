package com.dcankayrak.productservice.repository;


import com.dcankayrak.productservice.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {

}
