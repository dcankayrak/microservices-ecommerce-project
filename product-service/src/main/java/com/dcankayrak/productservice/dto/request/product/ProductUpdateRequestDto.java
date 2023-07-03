package com.dcankayrak.productservice.dto.request.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDto {
    private String name;
    private BigDecimal price;
    private String description;
}
