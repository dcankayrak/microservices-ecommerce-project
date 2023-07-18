package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.productOrder.ProductOrderSaveRequestDto;
import com.dcankayrak.productservice.dto.response.CartListResponseDto;
import com.dcankayrak.productservice.dto.response.OrderListByUserResponseDto;
import com.dcankayrak.productservice.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @GetMapping("/user/{userId}")
    private ResponseEntity<List<OrderListByUserResponseDto>> getOrdersByUser(@PathVariable Long userId){
        return new ResponseEntity<>(this.productOrderService.getOrdersByUser(userId),HttpStatus.OK);
    }


//    @PostMapping("/order")
//    public ResponseEntity<Void> createOrder(@RequestBody ProductOrderSaveRequestDto request){
//        this.productOrderService.saveProductOrder(request);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping("/create/order/{userId}")
    public ResponseEntity<Void> createOrder(@RequestBody List<ProductOrderSaveRequestDto> request,@PathVariable Long userId){
        this.productOrderService.createProductOrders(request,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
