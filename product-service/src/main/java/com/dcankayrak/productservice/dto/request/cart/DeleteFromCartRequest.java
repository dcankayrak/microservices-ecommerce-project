package com.dcankayrak.productservice.dto.request.cart;

import lombok.Data;

@Data
public class DeleteFromCartRequest {
    private Long userId;
    private String productSlug;
}
