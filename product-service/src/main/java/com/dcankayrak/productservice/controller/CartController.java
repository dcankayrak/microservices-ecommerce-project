package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.cart.AddToCartRequestDto;
import com.dcankayrak.productservice.dto.request.cart.DeleteAllCartRequestDto;
import com.dcankayrak.productservice.dto.request.cart.DeleteFromCartRequest;
import com.dcankayrak.productservice.dto.response.CartListResponseDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartListResponseDto>> getCart(@PathVariable Long userId){
        return new ResponseEntity<>(this.cartService.getCart(userId),HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Void> addToCart(@RequestBody AddToCartRequestDto request){
        this.cartService.addToCart(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<Void> clearCart(@RequestBody DeleteAllCartRequestDto request){
        this.cartService.clearCart(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromCart(@RequestBody DeleteFromCartRequest request){
        this.cartService.deleteFromCart(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
