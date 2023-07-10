package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.CategoryConverter;
import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.core.Slugify;
import com.dcankayrak.productservice.dto.request.category.CategorySaveRequestDto;
import com.dcankayrak.productservice.dto.request.category.CategoryUpdateRequestDto;
import com.dcankayrak.productservice.dto.response.CategoryListResponseDto;
import com.dcankayrak.productservice.entity.Category;
import com.dcankayrak.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteCategory(Long categoryId) {
        Category tempCategory = this.categoryRepository
                .findById(categoryId).orElseThrow(() -> new RuntimeException("Böyle bir kategori bulunmamaktadır"));
        categoryRepository.delete(tempCategory);
    }

    public void updateCategory(Long categoryId, CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category tempCategory = this.categoryRepository
                .findById(categoryId).orElseThrow(() -> new RuntimeException("Böyle bir kategori bulunmamaktadır"));
        tempCategory.setName(categoryUpdateRequestDto.getName());
        categoryRepository.save(tempCategory);
    }
}
