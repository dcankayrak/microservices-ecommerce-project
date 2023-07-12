package com.dcankayrak.productservice.entity;

import com.dcankayrak.productservice.core.Slugify;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.io.BigDecimalParser;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import lombok.Builder;
import org.apache.commons.text.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount_rate")
    private Double discountRate;
    @Column(name = "after_discount")
    private BigDecimal afterDiscount;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        if(discountRate<0 ||discountRate>100){
            discountRate = 0.0;
        }
        this.discountRate = discountRate;
    }

    public BigDecimal getAfterDiscount() {
        return price.subtract(price.multiply(new BigDecimal(discountRate)).divide(new BigDecimal(100)));
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
