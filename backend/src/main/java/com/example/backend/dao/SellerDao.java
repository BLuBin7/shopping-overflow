package com.example.backend.dao;


import com.example.backend.entity.Seller;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Binh
 * Date : 7/30/2023 - 2:48 PM
 * Description :
 */
public interface SellerDao extends CrudRepository<Seller,String> {
}
