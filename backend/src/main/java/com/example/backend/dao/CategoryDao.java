package com.example.backend.dao;

import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CategoryDao extends CrudRepository<Category, Integer>{
//    public Iterable<Category> findAllByProductIdAndCategory_name(List<Integer> productIds, String categoryName);
}
