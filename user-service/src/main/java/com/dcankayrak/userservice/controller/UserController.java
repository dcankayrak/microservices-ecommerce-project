package com.dcankayrak.userservice.controller;

import com.dcankayrak.userservice.dto.request.UserLoginRequestDto;
import com.dcankayrak.userservice.dto.request.UserRegisterRequestDto;
import com.dcankayrak.userservice.dto.response.ProductListItemResponseDto;
import com.dcankayrak.userservice.dto.response.UserLoginResponseDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserWithId(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.getUserWithId(id),HttpStatus.OK);
    }

    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> isUserExists(@PathVariable Long userId){
        return new ResponseEntity<>(this.userService.isUserExists(userId),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto request){
        UserLoginResponseDto result = this.userService.login(request);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserLoginResponseDto> register(@RequestBody UserRegisterRequestDto request){
        return new ResponseEntity<>(this.userService.saveUser(request),HttpStatus.CREATED);
    }
}
