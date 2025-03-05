package com.blubin.promotionservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PromotionCategoryId implements java.io.Serializable {
    private static final long serialVersionUID = -2552771402027667829L;
    @NotNull
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @NotNull
    @Column(name = "promotion_id", nullable = false)
    private Integer promotionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PromotionCategoryId entity = (PromotionCategoryId) o;
        return Objects.equals(this.categoryId, entity.categoryId) &&
                Objects.equals(this.promotionId, entity.promotionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, promotionId);
    }

}