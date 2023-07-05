package com.dcankayrak.productservice.schedule;

import com.dcankayrak.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSchedule {

    private final ProductService productService;

    @Scheduled(fixedDelay = 1000)
    public void productListRefresh(){
        productService.getProductListPut();
    }
}
