package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.CategoryConverter;
import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.core.Slugify;
import com.dcankayrak.productservice.dto.request.category.CategorySaveRequestDto;
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
    private final GeneralConverter generalConverter;
    private final Slugify slugify;

    public List<CategoryListResponseDto> getCategories(){
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryListResponseDto> tempCategories = generalConverter.convertEntitiesToTargetEntity(categories,CategoryListResponseDto.class);
        return tempCategories;
    }

    public void saveCategory(CategorySaveRequestDto categorySaveRequestDto) {
        Category tempCategory = generalConverter.convertEntityToTargetEntity(categorySaveRequestDto,Category.class);
        tempCategory.setSlug(slugify.slugify(tempCategory.getName(),'c'));
        this.categoryRepository.save(tempCategory);
    }
}
