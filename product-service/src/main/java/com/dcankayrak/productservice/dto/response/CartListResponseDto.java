package com.dcankayrak.productservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartListResponseDto {

    private ProductListResponseDto product;
    private int quantity;
}
