package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.dto.response.CategoryListResponseDto;
import com.dcankayrak.productservice.entity.Category;
import com.dcankayrak.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponseDto> getCategories(){
        return null;
    }
}
