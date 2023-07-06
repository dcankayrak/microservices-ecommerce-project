package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.productOrder.ProductOrderSaveRequestDto;
import com.dcankayrak.productservice.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/productOrder")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @PostMapping("/order")
    public ResponseEntity<Void> createOrder(@RequestBody ProductOrderSaveRequestDto request){
        this.productOrderService.saveProductOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
