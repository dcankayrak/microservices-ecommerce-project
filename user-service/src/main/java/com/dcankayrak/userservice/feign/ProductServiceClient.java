package com.dcankayrak.userservice.feign;

import com.dcankayrak.userservice.dto.response.ProductListItemResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(name = "productService",url = "http://localhost:8080/")
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET,value = "/products")
    List<ProductListItemResponseDto> getProducts();
}
