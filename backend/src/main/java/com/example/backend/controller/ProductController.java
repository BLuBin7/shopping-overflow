package com.example.backend.controller;

import com.example.backend.dao.ProductDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.ImageModel;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.service.JwtService;
import com.example.backend.service.ProductService;
import com.example.backend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtUtil;

//    @PreAuthorize("hasRole('Admin')")
    // nhiều nhiều
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PostMapping(value = {"/addNewProduct"}, produces = {MediaType.IMAGE_PNG_VALUE,"application/json"})
//    @PostMapping(value = "/addNewProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // lôi là do kiểu chuỗi mới đúng ??
    public Product addNewProduct(@RequestPart("product") String productJson
                                 , @RequestPart("imageFile") MultipartFile[] file,
                                 HttpServletRequest request) {
        try {
            Set<ImageModel> images = uploadImage(file);
            ObjectMapper objectMapper = new ObjectMapper();
            // Object Mapperđược sử dụng để giải tuần tự hóa
            // productJson chuỗi thành một Product đối tượng
            Product product = objectMapper.readValue(productJson, Product.class);
            product.setProductImages(images);

            // thêm tên người tạo
            String jwtToken = request.getHeader("Authorization");
            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
            }

            String username = jwtUtil.getUsernameFromToken(jwtToken);
            jwtService.loadUserByUsername(username);
            // Decode the JWT token to get user information (assuming you have a JwtUtil for this purpose)
            User authenticatedUser = userDao.findById(username).orElse(null);
            if (authenticatedUser != null) {

                product.setAddedBy(authenticatedUser);
            }

            //add
//            User user = new User();
//            user.setSold(product);
//            userDao.save(user);
            ///
            return productService.addNewProduct(product);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    // 1 nhiều
//    @PreAuthorize("hasRole('Admin')")
//    @PostMapping("/addNewProduct")
//    public Product addNewProduct(@RequestParam("product") String productJson,
//                                 @RequestParam("imageFile") MultipartFile[] imageFiles) {
//        try {
//            // chuyển object thành json
//            ObjectMapper objectMapper = new ObjectMapper();
//            // Object Mapperđược sử dụng để giải tuần tự hóa
//            // productJson chuỗi thành một Product đối tượng
//            Product product = objectMapper.readValue(productJson, Product.class);
//            Set<ImageModel> images = new HashSet<>();
//
//            for (MultipartFile file : imageFiles) {
//                ImageModel imageModel = new ImageModel();
//                imageModel.setName(file.getOriginalFilename());
//                imageModel.setType(file.getContentType());
//                imageModel.setPicByte(file.getBytes());
//                images.add(imageModel);
//            }
//
//            product.setProductImages(images);
//            return productService.addNewProduct(product);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            // If there is no authenticated user, you may choose to handle it appropriately (e.g., throw an exception).
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }

        // If the principal is not an instance of User (your custom user details class), handle it accordingly.
        // For example, if you are using Spring Security's default UserDetails, you can cast it to UserDetails.
        // UserDetails userDetails = (UserDetails) principal;
        // Then you can retrieve the user details from the userDetails object.

        return null;
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "") String searchKey) {
        List<Product> result = productService.getAllProducts(pageNumber, searchKey);
        System.out.println("Result size is "+ result.size());
        return result;
    }

    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId) {
        return productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId) {
        productService.deleteProductDetails(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout" ) boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId")  Integer productId) {
        return productService.getProductDetails(isSingleProductCheckout, productId);
    }
}
