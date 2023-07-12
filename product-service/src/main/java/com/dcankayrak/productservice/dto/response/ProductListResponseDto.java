package com.dcankayrak.productservice.dto.response;

import com.dcankayrak.productservice.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductListResponseDto {
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double discountRate;
    private BigDecimal afterDiscount;
    private String slug;
}
