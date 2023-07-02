package com.dcankayrak.productservice.converter;

import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper mapper;

    public List<ProductListResponseDto> convertProductsToProductListResponseDto(List<Product> list){
        List<ProductListResponseDto> resultList = new ArrayList<>();
        for (Product item:list) {
            resultList.add(convertProductToProductListResponseDto(item));
        }
        return resultList;
    }

    public ProductListResponseDto convertProductToProductListResponseDto(Product product){
        return mapper.map(product,ProductListResponseDto.class);
    }
}

