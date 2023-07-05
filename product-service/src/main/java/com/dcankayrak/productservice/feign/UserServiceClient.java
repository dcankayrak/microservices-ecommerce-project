package com.dcankayrak.productservice.feign;

import com.dcankayrak.productservice.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userService",url = "http://localhost:8095/users")
public interface UserServiceClient {

    @GetMapping
    UserResponseDto findUserById(@RequestParam Long id);
}
