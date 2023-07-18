package com.dcankayrak.productservice.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class OrderListByUserResponseDto {
    private String date;
    private ProductListResponseDto product;
    private int quantity;
}
