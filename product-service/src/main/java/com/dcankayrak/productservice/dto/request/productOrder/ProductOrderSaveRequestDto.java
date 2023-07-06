package com.dcankayrak.productservice.dto.request.productOrder;

import lombok.Data;

@Data
public class ProductOrderSaveRequestDto {
    private Long productId;
    private Long orderId;
    private Long customerId;
}
