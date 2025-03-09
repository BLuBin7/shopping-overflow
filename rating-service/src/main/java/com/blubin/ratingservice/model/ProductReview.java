package com.blubin.ratingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id",updatable = false, nullable = false)
    private UUID id;

    @Column(name = "product_id")
    private UUID product;

    @Column(name = "user_id")
    private UUID user;

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