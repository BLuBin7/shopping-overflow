package com.blubin.cartservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {
    @EmbeddedId
    private ShoppingCartItemId id;

    @MapsId("cartId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCart cart;

    @MapsId("productItemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItem productItem;

    @Column(name = "quantity")
    private Integer quantity;

}