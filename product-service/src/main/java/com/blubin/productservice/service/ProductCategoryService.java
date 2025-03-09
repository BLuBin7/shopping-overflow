package com.blubin.productservice.service;

import com.blubin.commonservice.exception.DuplicatedException;
import com.blubin.productservice.model.ProductCategory;
import com.blubin.productservice.repository.ProductCategoryRepository;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryPostVm;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    private boolean checkExistedName(String categoryName, UUID id) {
        return productCategoryRepository.findExistedName(categoryName, id) != null;
    }

    private void validExistedName(String categoryName, UUID categoryId) {
        if(checkExistedName(categoryName, categoryId)) {
            throw new DuplicatedException(Constants.ErrorCodes.CATEGORY_NAME_ALREADY_EXITED,categoryName);
        }
    }

    public ProductCategory create(ProductCategoryPostVm productCategoryPostVm){
        validExistedName(productCategoryPostVm.categoryName(),null);
        return productCategoryRepository.save(productCategoryPostVm.toModel());
    }
}
