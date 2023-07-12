package com.dcankayrak.productservice.dto.response;

import com.dcankayrak.productservice.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryListResponseDto {
    private String name;
    private String slug;
    private List<Product> products;
}
