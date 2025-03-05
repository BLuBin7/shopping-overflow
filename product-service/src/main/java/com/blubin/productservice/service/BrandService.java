package com.blubin.productservice.service;

import com.blubin.commonservice.exception.DuplicatedException;
import com.blubin.commonservice.exception.NotFoundException;
import com.blubin.productservice.model.Brand;
import com.blubin.productservice.repository.BrandRepository;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.viewmodel.brand.BrandGetVm;
import com.blubin.productservice.viewmodel.brand.BrandListGetVm;
import com.blubin.productservice.viewmodel.brand.BrandPostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    private boolean checkExistedName(String brandName, Long id) {
        return brandRepository.findExistedName(brandName, id) != null;
    }

    private void validExistedName(String brandName, Long brandId) {
        if(checkExistedName(brandName, brandId)) {
            throw new DuplicatedException(Constants.ErrorCodes.NAME_ALREADY_EXITED,brandName);
        }
    }

    public Brand create(BrandPostVm brandPostVm){
        validExistedName(brandPostVm.name(),null);
        return brandRepository.save(brandPostVm.toModel());
    }

    public BrandListGetVm getBrandList(int pageNo, int pageSize) {
        List<BrandGetVm> brandGetVms = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        List<Brand> brandList = brandPage.getContent();

        for (Brand brand : brandList) {
            brandGetVms.add(BrandGetVm.fromModel(brand));
        }

        return new BrandListGetVm(
                brandGetVms,
                brandPage.getNumber(),
                brandPage.getSize(),
                (int) brandPage.getTotalElements(),
                brandPage.getTotalPages(),
                brandPage.isLast()
        );
    }

    public Brand update(BrandPostVm brandPostVm, Long id) {
        validExistedName(brandPostVm.name(), id);

        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.ErrorCodes.BRAND_NOT_FOUND, id));
        brand.setBrandDescription(brandPostVm.description());
        brand.setBrandName(brandPostVm.name());

        return brandRepository.save(brand);
    }

    public void delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new NotFoundException(Constants.ErrorCodes.BRAND_NOT_FOUND, id));
//        if (!brand.getProducts().isEmpty()) {
//            throw new BadRequestException(Constants.ErrorCodes.MAKE_SURE_BRAND_DONT_CONTAINS_ANY_PRODUCT);
//        }
        brandRepository.deleteById(id);
    }
}
