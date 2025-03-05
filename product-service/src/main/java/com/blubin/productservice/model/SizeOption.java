package com.blubin.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "size_option")
public class SizeOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "size_name", nullable = false, length = 100)
    private String sizeName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_category_id")
    private SizeCategory sizeCategory;

}