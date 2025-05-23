package com.blubin.productservice.repository;

import com.blubin.productservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

    @Query("select e from Brand e where e.brandName = ?1 and (?2 is null or e.id != ?2)")
    Brand findExistedName(String brandName, UUID id);
}
