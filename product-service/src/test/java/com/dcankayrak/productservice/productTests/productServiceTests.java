package com.dcankayrak.productservice.productTests;

import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class productServiceTests {

    private final ProductRepository productRepository;

    @Test
    public void itShouldGetAllProducts(){

    }
}
