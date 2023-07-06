package com.dcankayrak.productservice.feign;

import com.dcankayrak.productservice.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userService",url = "http://localhost:8095/api/v1/users")
public interface UserServiceClient {

    @GetMapping
    UserResponseDto findUserById(@RequestParam Long id);

    @GetMapping("/exists/{userId}")
    Boolean isUserExists(@PathVariable Long userId);
}
