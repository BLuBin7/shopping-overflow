package com.example.backend.controller;

import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.service.CategoryService;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void initCategory() {
        categoryService.initCategory();
    }

    @GetMapping("/getAllProductbasedonCategory/{categoryId}")
    public List<Product> getAllProductbasedonCategory(@RequestParam(defaultValue = "0") int pageNumber
                                     , @PathVariable("categoryId") Integer id){
        List<Product> list = categoryService.listProductbasedonCategory(pageNumber,id);
        return list;
    }
}
