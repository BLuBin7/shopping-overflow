package com.blubin.cartservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class ShoppingCartItemId implements java.io.Serializable {
    private static final long serialVersionUID = -1478894238683425865L;
    @NotNull
    @Column(name = "cart_id", nullable = false)
    private UUID cartId;

    @NotNull
    @Column(name = "product_item_id", nullable = false)
    private UUID productItemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShoppingCartItemId entity = (ShoppingCartItemId) o;
        return Objects.equals(this.cartId, entity.cartId) &&
                Objects.equals(this.productItemId, entity.productItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productItemId);
    }

}