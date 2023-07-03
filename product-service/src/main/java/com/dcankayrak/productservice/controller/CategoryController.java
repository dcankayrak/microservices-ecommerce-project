package com.dcankayrak.productservice.controller;

import com.dcankayrak.productservice.dto.request.category.CategorySaveRequestDto;
import com.dcankayrak.productservice.dto.response.CategoryListResponseDto;
import com.dcankayrak.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping
    public ResponseEntity<List<CategoryListResponseDto>> getCategories(){
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto){
        this.categoryService.saveCategory(categorySaveRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
