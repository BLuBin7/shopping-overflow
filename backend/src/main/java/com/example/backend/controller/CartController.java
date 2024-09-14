package com.example.backend.controller;

import com.example.backend.dao.CartDao;
import com.example.backend.entity.Cart;
import com.example.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartDao cartDao;
    @Resource(name = "redisTemplate")
    private HashOperations<String, Integer, Cart > hashOperationsCsrt;

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public Cart addToCart(@PathVariable(name = "productId") Integer productId) {
        Cart cart = cartDao.findById(productId).get();
        hashOperationsCsrt.put("Cart",cart.getCartId(),cart);
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteCartItem(@PathVariable(name = "cartId") Integer cartId) {
        cartService.deleteCartItem(cartId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();
    }
}
