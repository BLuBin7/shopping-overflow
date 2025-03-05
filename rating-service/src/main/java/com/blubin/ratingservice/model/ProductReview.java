package com.blubin.ratingservice.model;

import com.blubin.productservice.model.Product;
import com.blubin.userservice.model.SiteUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SiteUser user;

    @Size(max = 100)
    @Column(name = "review_title", length = 100)
    private String reviewTitle;

    @Column(name = "review_rating")
    private Integer reviewRating;

    @Column(name = "review_comment", length = Integer.MAX_VALUE)
    private String reviewComment;

    @NotNull
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

}