package com.dcankayrak.productservice.dto.response;

import lombok.Data;

@Data
public class OrderListByUserResponseDto {
    private String orderNumber;
    private ProductListResponseDto product;
    private int quantity;
}
