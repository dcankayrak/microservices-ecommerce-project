package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.dto.request.cart.AddToCartRequestDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.CartItem;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.exception.ProductNotFoundException;
import com.dcankayrak.productservice.feign.UserServiceClient;
import com.dcankayrak.productservice.repository.CartRepository;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserServiceClient userServiceClient;
    private final GeneralConverter generalConverter;
    public void addToCart(AddToCartRequestDto request) {
        Boolean isUserExists = this.userServiceClient.isUserExists(request.getUserId());
        Product tempProduct = this.productRepository.findBySlug(request.getProductSlug());

        if(tempProduct != null && isUserExists){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(tempProduct);
            cartItem.setUserId(request.getUserId());
            this.cartRepository.save(cartItem);
            return;
        }
        throw new ProductNotFoundException("Aradığınız ürün bulunamadı!");
    }

    public List<ProductListResponseDto> getCart(Long userId) {
        Boolean isUserExists = this.userServiceClient.isUserExists(userId);

        if(isUserExists){
            List<Product> tempList = this.productRepository.findProductsFromCartByUserId(userId);
            return this.generalConverter.convertEntitiesToTargetEntity(tempList,ProductListResponseDto.class);
        }
        throw new RuntimeException("Aradığınız kullanıcı bulunamadı!");
    }
}
