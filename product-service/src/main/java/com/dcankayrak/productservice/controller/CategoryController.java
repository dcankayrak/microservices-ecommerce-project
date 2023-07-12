package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.category.CategorySaveRequestDto;
import com.dcankayrak.productservice.dto.request.category.CategoryUpdateRequestDto;
import com.dcankayrak.productservice.dto.response.CategoryListResponseDto;
import com.dcankayrak.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponseDto>> getCategories(){
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryListResponseDto> getCategoryWithSlug(@PathVariable String slug){
        return new ResponseEntity<>(this.categoryService.getCategoryWithSlug(slug), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto){
        this.categoryService.saveCategory(categorySaveRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        this.categoryService.updateCategory(categoryId,categoryUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@RequestBody Long categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
