package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.ProductSaveRequestDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    /**
     * Gets all product list items
     * @return List<ProductListResponseDto>
     */
    @GetMapping
    public ResponseEntity<List<ProductListResponseDto>> getProductList(){
        return new ResponseEntity<>(this.productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/{productSlug}")
    public ResponseEntity<ProductListResponseDto> getProductWithSlug(@PathVariable String productSlug){
        return new ResponseEntity<>(this.productService.getProductWithSlug(productSlug), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductSaveRequestDto request){
        this.productService.saveProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
