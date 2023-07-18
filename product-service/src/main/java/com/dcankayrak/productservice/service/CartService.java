package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.dto.request.cart.AddToCartRequestDto;
import com.dcankayrak.productservice.dto.request.cart.DeleteAllCartRequestDto;
import com.dcankayrak.productservice.dto.request.cart.DeleteFromCartRequest;
import com.dcankayrak.productservice.dto.response.CartListResponseDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.CartItem;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.exception.ProductNotFoundException;
import com.dcankayrak.productservice.feign.UserServiceClient;
import com.dcankayrak.productservice.repository.CartRepository;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

        Optional<CartItem> isCartItemExists = this.cartRepository.findByUserIdAndProduct(request.getUserId(), tempProduct.getId());

        if(tempProduct != null && isUserExists){

            if(isCartItemExists.isPresent()){
                CartItem item = isCartItemExists.get();
                int quantity = item.getQuantity();
                item.setQuantity(quantity+1);
                cartRepository.save(item);
                return;
            }
            CartItem cartItem = new CartItem();
            cartItem.setProduct(tempProduct);
            cartItem.setUserId(request.getUserId());
            cartItem.setQuantity(1);
            this.cartRepository.save(cartItem);
            return;
        }
        throw new ProductNotFoundException("Aradığınız ürün bulunamadı!");
    }

    public List<CartListResponseDto> getCart(Long userId) {
        Boolean isUserExists = this.userServiceClient.isUserExists(userId);
        List<CartListResponseDto> list = new ArrayList<>();
        if(isUserExists){
            List<CartItem> tempList = this.cartRepository.findByUserId(userId);
            tempList.forEach(item -> {
                CartListResponseDto cartListResponseDto = new CartListResponseDto();
                cartListResponseDto.setProduct(generalConverter.convertEntityToTargetEntity(item.getProduct(),ProductListResponseDto.class));
                cartListResponseDto.setQuantity(item.getQuantity());
                list.add(cartListResponseDto);
            });
            return list;
        }
        throw new RuntimeException("Aradığınız kullanıcı bulunamadı!");
    }

    public void clearCart(DeleteAllCartRequestDto request) {
        Boolean isUserExists = this.userServiceClient.isUserExists(request.getUserId());
        if(isUserExists){
            List<CartItem> items = this.cartRepository.findByUserId(request.getUserId());
            for (CartItem item:items
                 ) {
                this.cartRepository.delete(item);
            }
            return;
        }
        throw new RuntimeException("Kullanıcı Bulunamadı!");
    }

    public void deleteFromCart(DeleteFromCartRequest request) {
        Product tempProduct = this.productRepository.findBySlug(request.getProductSlug());
        Optional<CartItem> tempItem = this.cartRepository.findByUserIdAndProduct(request.getUserId(),tempProduct.getId());

        if(tempItem.isPresent() && tempProduct != null){
            this.cartRepository.delete(tempItem.get());
            return;
        }
        throw new ProductNotFoundException("Silmeye çalıştığınız ürün bulunmamakta.");
    }
}
