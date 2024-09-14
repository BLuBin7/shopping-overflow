package com.example.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity(name = "Cart")
@Table(name = "\"cart\"")
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Cart {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    public Cart(){

    }

    public Cart(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
