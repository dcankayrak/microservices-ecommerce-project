package com.dcankayrak.productservice.dto.request.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSaveRequestDto {
    private String name;
    private BigDecimal price;
    private String description;
    private Double discountRate;
    private String imageUrl;
    private Long categoryId;
}
