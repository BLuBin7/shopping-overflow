package com.blubin.productservice.model;

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
public class ProductAttributeId implements java.io.Serializable {
    private static final long serialVersionUID = -5905382189716996702L;
    @NotNull
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull
    @Column(name = "attribute_option_id",updatable = false, nullable = false)
    private UUID attributeOptionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductAttributeId entity = (ProductAttributeId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.attributeOptionId, entity.attributeOptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, attributeOptionId);
    }

}