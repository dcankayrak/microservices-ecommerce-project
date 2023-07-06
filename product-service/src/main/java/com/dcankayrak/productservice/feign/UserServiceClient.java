package com.dcankayrak.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userService",url = "http://localhost:8095/api/v1/users")
public interface UserServiceClient {
    @GetMapping("/exists/{userId}")
    Boolean isUserExists(@PathVariable Long userId);
}
