package com.dcankayrak.productservice.repository;

import com.dcankayrak.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findBySlug(String slug);
}
