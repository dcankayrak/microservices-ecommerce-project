package com.dcankayrak.productservice.repository;


import com.dcankayrak.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
