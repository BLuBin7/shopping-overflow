package com.blubin.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Size(max = 400)
    @Column(name = "category_image", length = 400)
    private String categoryImage;

    @Column(name = "category_description", length = Integer.MAX_VALUE)
    private String categoryDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_category_id")
    private ProductCategory parentProductCategory;

}