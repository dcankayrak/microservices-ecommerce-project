package com.dcankayrak.productservice.repository;

import com.dcankayrak.productservice.entity.Category;
import com.dcankayrak.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findBySlug(String slug);

    @Query(value = "SELECT p FROM Product p WHERE p.id IN (SELECT po.product.id FROM ProductOrder po WHERE po.order.id = :orderId)")
    List<Product> findProductsByOrderId(@Param("orderId") Long orderId);

    @Query(value = "SELECT p FROM Product p WHERE p.id IN (SELECT c.product.id FROM CartItem c WHERE c.userId = :userId)")
    List<Product> findProductsFromCartByUserId(@Param("userId") Long userId);

    List<Product> findByCategory(Category category);
}
