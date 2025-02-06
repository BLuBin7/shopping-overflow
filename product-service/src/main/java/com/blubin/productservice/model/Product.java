package com.blubin.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 500)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 500)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @Column(name = "product_description", length = Integer.MAX_VALUE)
    private String productDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Size(max = 50)
    @Column(name = "model_height", length = 50)
    private String modelHeight;

    @Size(max = 50)
    @Column(name = "model_wearing", length = 50)
    private String modelWearing;

    @Column(name = "care_instructions", length = Integer.MAX_VALUE)
    private String careInstructions;

    @Column(name = "about", length = Integer.MAX_VALUE)
    private String about;

}