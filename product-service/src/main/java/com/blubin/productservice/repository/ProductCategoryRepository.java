package com.blubin.productservice.repository;

import com.blubin.productservice.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("select e from ProductCategory e where e.categoryName = ?1 and (?2 is null or e.id != ?2)")
    ProductCategory findExistedName(String categoryName, Long id);
}
