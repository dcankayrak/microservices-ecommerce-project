package com.dcankayrak.productservice.dto.request.productOrder;

import lombok.Data;

@Data
public class ProductOrderSaveRequestDto {
    private String productSlug;
    private int quantity;
}
