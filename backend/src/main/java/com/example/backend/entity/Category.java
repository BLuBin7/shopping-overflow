package com.example.backend.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Binh
 * Date : 10/8/2023 - 5:47 PM
 * Description :
 */
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Builder
@Entity(name = "Category")
@Table(name = "\"Category\"")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;

//    @OneToOne
//    private Product product;
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
