package com.dcankayrak.userservice.controller;

import com.dcankayrak.userservice.dto.response.ProductListItemResponseDto;
import com.dcankayrak.userservice.feign.ProductServiceClient;
import com.dcankayrak.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductServiceClient productServiceClient;

    @RequestMapping("/test")
    public List<ProductListItemResponseDto> test(){
        return productServiceClient.getProducts();
    }
}
