package com.dcankayrak.productservice.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import org.apache.commons.text.*;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String description;
    private String slug;
}
