package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.core.Slugify;
import com.dcankayrak.productservice.dto.request.category.CategorySaveRequestDto;
import com.dcankayrak.productservice.dto.request.category.CategoryUpdateRequestDto;
import com.dcankayrak.productservice.dto.response.CategoryListResponseDto;
import com.dcankayrak.productservice.entity.Category;
import com.dcankayrak.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final GeneralConverter generalConverter;
    private final Slugify slugify;

    @Cacheable(value = "categoryList",cacheManager = "defaultCacheManager")
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

    public CategoryListResponseDto getCategoryWithSlug(String slug) {
        Category tempCategory = this.categoryRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Böyle bir kategori bulunmamaktadır"));
        return this.generalConverter.convertEntityToTargetEntity(tempCategory,CategoryListResponseDto.class);
    }
}
