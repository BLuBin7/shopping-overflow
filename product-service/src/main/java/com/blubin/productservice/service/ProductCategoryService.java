package com.blubin.productservice.service;

import com.blubin.commonservice.exception.DuplicatedException;
import com.blubin.commonservice.exception.NotFoundException;
import com.blubin.productservice.model.ProductCategory;
import com.blubin.productservice.repository.ProductCategoryRepository;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.viewmodel.product.ProductGetVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryGetVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryListGerVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryPostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public ProductCategoryListGerVm getProductCategoryList(int pageNo, int pageSize) {
        List<ProductCategoryGetVm> productcategoryGetVms = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductCategory> productcategoryPage = productCategoryRepository.findAll(pageable);
        List<ProductCategory> productCategoryList = productcategoryPage.getContent();

        for (ProductCategory productCategory : productCategoryList) {
            productcategoryGetVms.add(ProductCategoryGetVm.fromModel(productCategory));
        }

        return new ProductCategoryListGerVm(
                productcategoryGetVms,
                productcategoryPage.getNumber(),
                productcategoryPage.getSize(),
                (int) productcategoryPage.getTotalElements(),
                productcategoryPage.getTotalPages(),
                productcategoryPage.isLast()
        );
    }

    public ProductCategory updateProductCategory(ProductCategoryPostVm productCategoryPostVm, UUID id) {
        validExistedName(productCategoryPostVm.categoryName(),id);

        ProductCategory productCategory = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.ErrorCodes.PRODUCT_CATEGORY_NOT_FOUND));

        productCategory.setCategoryName(productCategoryPostVm.categoryName());
        productCategory.setCategoryImage(productCategoryPostVm.categoryImage());

        Optional<ProductCategory> parentProductCategoryOptional = productCategoryRepository.findById(id);

        productCategory.setParentProductCategory(
                parentProductCategoryOptional.orElseThrow(() -> new NotFoundException(Constants.ErrorCodes.PRODUCT_CATEGORY_NOT_FOUND))
        );

        return productCategoryRepository.save(productCategory);
    }

}
