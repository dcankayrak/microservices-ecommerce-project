package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.ProductConverter;
import com.dcankayrak.productservice.dto.request.ProductSaveRequestDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<ProductListResponseDto> getProductList() {
        return productConverter.convertProductsToProductListResponseDto(productRepository.findAll());
    }

    public ProductListResponseDto getProductWithSlug(String slug) {
        return productConverter.convertProductToProductListResponseDto(productRepository.findBySlug(slug));
    }

    public void saveProduct(ProductSaveRequestDto request) {
        Product tempProduct = new Product();
        tempProduct.setName(request.getName());
        tempProduct.setPrice(request.getPrice());
        tempProduct.setDescription(request.getDescription());
        tempProduct.setSlug(request.getName());
        productRepository.save(tempProduct);
    }
}
