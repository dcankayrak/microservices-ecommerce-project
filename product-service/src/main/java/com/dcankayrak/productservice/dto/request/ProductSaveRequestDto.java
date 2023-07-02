package com.dcankayrak.productservice.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSaveRequestDto {
    private String name;
    private BigDecimal price;
    private String description;
}
