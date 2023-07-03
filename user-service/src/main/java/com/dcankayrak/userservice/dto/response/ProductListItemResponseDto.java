package com.dcankayrak.userservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductListItemResponseDto {
    private String name;
    private BigDecimal price;
    private String description;
}
