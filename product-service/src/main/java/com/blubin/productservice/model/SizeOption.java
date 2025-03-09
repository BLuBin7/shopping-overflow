package com.blubin.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "size_option")
public class SizeOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "size_id",updatable = false, nullable = false)
    private UUID id;

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