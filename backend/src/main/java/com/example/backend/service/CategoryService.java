package com.example.backend.service;

import com.example.backend.dao.CategoryDao;
import com.example.backend.dao.ProductDao;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;
    public List<Product> listProductbasedonCategory(int pageNumber, Integer categoryId) {
        Pageable pageable = PageRequest.of(pageNumber, 30);

        Category category = categoryDao.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Danh mục không tồn tại"));

        Page<Product> productPage = productDao.findByCategory(category, pageable);

        return productPage.getContent();
    }

    public void initCategory(){
        Category category1 = new Category();
        category1.setCategoryId(1);
        category1.setCategoryName("Nữ");
        categoryDao.save(category1);

        Category category2 = new Category();
        category2.setCategoryId(2);
        category2.setCategoryName("Áo Tops");
        categoryDao.save(category2);

        Category category3 = new Category();
        category3.setCategoryId(3);
        category3.setCategoryName("Đầm");
        categoryDao.save(category3);

        Category category4 = new Category();
        category4.setCategoryId(4);
        category4.setCategoryName("Quần & Váy");
        categoryDao.save(category4);

        Category category5 = new Category();
        category5.setCategoryId(5);
        category5.setCategoryName("Đồ lót & Đồ mặc nhà");
        categoryDao.save(category5);

        Category category6 = new Category();
        category6.setCategoryId(6);
        category6.setCategoryName("Trang phục đi biển");
        categoryDao.save(category6);

        Category category7 = new Category();
        category7.setCategoryId(7);
        category7.setCategoryName("Thời Trang Nam");
        categoryDao.save(category7);

        Category category8 = new Category();
        category8.setCategoryId(8);
        category8.setCategoryName("Phụ Kiện");
        categoryDao.save(category8);

        Category category9 = new Category();
        category9.setCategoryId(9);
        category9.setCategoryName("Trẻ em");
        categoryDao.save(category9);

        Category category10 = new Category();
        category10.setCategoryId(10);
        category10.setCategoryName("Đồ Trang sức và Đồng hồ");
        categoryDao.save(category10);

        Category category11 = new Category();
        category11.setCategoryId(11);
        category11.setCategoryName("Kích thước lớn");
        categoryDao.save(category11);

        Category category12 = new Category();
        category12.setCategoryId(12);
        category12.setCategoryName("Nhà cửa & đời sống");
        categoryDao.save(category12);

        Category category13 = new Category();
        category13.setCategoryId(13);
        category13.setCategoryName("Làm đẹp & Sức khỏe");
        categoryDao.save(category13);

        Category category14 = new Category();
        category14.setCategoryId(14);
        category14.setCategoryName("Thiết bị điện tử");
        categoryDao.save(category14);
    }

}
