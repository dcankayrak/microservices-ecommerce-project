package com.dcankayrak.productservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailsResponseDto {
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Double discountRate;
    private BigDecimal afterDiscount;
    private String slug;
}
