package com.example.backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Binh
 * Date : 10/8/2023 - 5:47 PM
 * Description :
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table
public class Category {
    @Id
    private int categoryId;
    private String CategoryName;
    @OneToMany
    private Set<Product> product;

}
