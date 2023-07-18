package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.core.Slugify;
import com.dcankayrak.productservice.dto.request.product.ProductSaveRequestDto;
import com.dcankayrak.productservice.dto.request.product.ProductUpdateRequestDto;
import com.dcankayrak.productservice.dto.response.ProductDetailsResponseDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.Category;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.exception.ProductNotFoundException;
import com.dcankayrak.productservice.repository.CategoryRepository;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GeneralConverter generalConverter;
    private final CategoryRepository categoryRepository;
    private final Slugify slugify;

    @Cacheable(value = "productList",cacheManager = "defaultCacheManager")
    public List<ProductListResponseDto> getProductList() {
        return this.generalConverter.convertEntitiesToTargetEntity(productRepository.findAll(),ProductListResponseDto.class);
    }

    @CacheEvict(value = "productList",cacheManager = "defaultCacheManager")
    public List<ProductListResponseDto> getProductListPut() {
        return this.generalConverter.convertEntitiesToTargetEntity(productRepository.findAll(),ProductListResponseDto.class);
    }

    public ProductDetailsResponseDto getProductWithSlug(String slug) {
        return generalConverter.convertEntityToTargetEntity(productRepository.findBySlug(slug),ProductDetailsResponseDto.class);
    }

    public void saveProduct(ProductSaveRequestDto request) {
        Product tempProduct = new Product();
        tempProduct.setName(request.getName());
        tempProduct.setPrice(request.getPrice());
        tempProduct.setDescription(request.getDescription());
        tempProduct.setSlug(slugify.slugify(request.getName(),'p'));
        tempProduct.setDiscountRate(request.getDiscountRate());
        tempProduct.setImageUrl(request.getImageUrl());
        tempProduct.setCategory(
                this.categoryRepository.findById(request.getCategoryId()).orElseThrow( () -> new RuntimeException("Kategori bulunamadı!"))
        );
        productRepository.save(tempProduct);
    }

    public void deleteProduct(Long productId) {
        Product tempProduct = this.productRepository.findById(productId).orElseThrow(() -> {throw new ProductNotFoundException("Aradığınız ürün numarasına ait bir ürün bulunmamaktadır.");});
        this.productRepository.delete(tempProduct);
    }

    public ProductListResponseDto updateProduct(ProductUpdateRequestDto request, Long id) {
        Product tempProduct = this.productRepository.findById(id).orElseThrow(() -> {throw new ProductNotFoundException("Aradığınız ürün numarasına ait bir ürün bulunmamaktadır.");});
        tempProduct.setName(request.getName());
        tempProduct.setPrice(request.getPrice());
        tempProduct.setDescription(request.getDescription());
        this.productRepository.save(tempProduct);

        ProductListResponseDto tempProductListResponseDto = this.generalConverter
                .convertEntityToTargetEntity(tempProduct,ProductListResponseDto.class);
        return tempProductListResponseDto;
    }

    public List<ProductListResponseDto> getProductsWithOrder(Long orderId) {
        List<Product> products = this.productRepository.findProductsByOrderId(orderId);
        List<ProductListResponseDto> resultList = this.generalConverter.convertEntitiesToTargetEntity(products,ProductListResponseDto.class);
        return resultList;
    }

    public List<ProductListResponseDto> getProductsWithCategory(Long categoryId) {
        Category tempCategory = this.categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Kategori bulunamadı!"));
        List<ProductListResponseDto> resultList =  this.generalConverter
                .convertEntitiesToTargetEntity(this.productRepository.findByCategory(tempCategory),ProductListResponseDto.class);
        return resultList;
    }

    public List<ProductListResponseDto> getProductsWithCategorySlug(String categorySlug) {
        Category tempCategory = this.categoryRepository.findBySlug(categorySlug).orElseThrow(()-> new RuntimeException("Kategori bulunamadı!"));
        List<ProductListResponseDto> resultList =  this.generalConverter
                .convertEntitiesToTargetEntity(this.productRepository.findByCategory(tempCategory),ProductListResponseDto.class);
        return resultList;
    }
}
