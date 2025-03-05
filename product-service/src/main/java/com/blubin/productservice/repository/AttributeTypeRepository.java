package com.blubin.productservice.repository;

import com.blubin.productservice.model.AttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeTypeRepository extends JpaRepository<AttributeType, Long> {
}
