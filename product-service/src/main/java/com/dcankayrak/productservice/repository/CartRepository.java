package com.dcankayrak.productservice.repository;

import com.dcankayrak.productservice.entity.CartItem;
import com.dcankayrak.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT c FROM CartItem c where c.userId= :userId AND c.product.id=:product")
    Optional<CartItem> findByUserIdAndProduct(@Param("userId") Long userId, @Param("product") Long product);

    List<CartItem> findByUserId(Long userId);
}
