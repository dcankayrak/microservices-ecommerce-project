package com.dcankayrak.productservice.dto.request.cart;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long userId;
    private String productSlug;
}
