package com.dcankayrak.productservice.repository;


import com.dcankayrak.productservice.dto.response.OrderListByUserResponseDto;
import com.dcankayrak.productservice.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {

    @Query("FROM ProductOrder p WHERE p.userId = :userId ORDER BY p.createDate DESC")
    List<ProductOrder> findByUserId(Long userId);

}
