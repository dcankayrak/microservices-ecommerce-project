package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.product.ProductSaveRequestDto;
import com.dcankayrak.productservice.dto.request.product.ProductUpdateRequestDto;
import com.dcankayrak.productservice.dto.response.ProductDetailsResponseDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
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

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductListResponseDto>> getProductsWithCategory(@PathVariable Long categoryId){
        return new ResponseEntity<>(this.productService.getProductsWithCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/category/{categorySlug}")
    public ResponseEntity<List<ProductListResponseDto>> getProductsWithCategorySlug(@PathVariable String categorySlug){
        return new ResponseEntity<>(this.productService.getProductsWithCategorySlug(categorySlug), HttpStatus.OK);
    }

    @GetMapping("/details/{productSlug}")
    public ResponseEntity<ProductDetailsResponseDto> getProductWithSlug(@PathVariable String productSlug){
        return new ResponseEntity<>(this.productService.getProductWithSlug(productSlug), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ProductListResponseDto>> getProductsWithOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(this.productService.getProductsWithOrder(orderId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductSaveRequestDto request){
        this.productService.saveProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductListResponseDto> updateProduct(@RequestBody ProductUpdateRequestDto request, @RequestParam Long id){
        ProductListResponseDto tempProductListResponseDto = this.productService.updateProduct(request,id);
        return new ResponseEntity<>(tempProductListResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long productId){
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
