package com.dcankayrak.userservice.controller;

import com.dcankayrak.userservice.dto.request.UserRegisterRequestDto;
import com.dcankayrak.userservice.dto.response.ProductListItemResponseDto;
import com.dcankayrak.userservice.entity.User;
import com.dcankayrak.userservice.feign.ProductServiceClient;
import com.dcankayrak.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/{id}")
    public ResponseEntity<User> getUserWithId(@RequestParam Long id){
        return this.userService.getUserWithId(id);
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequestDto request){
        this.userService.saveUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
