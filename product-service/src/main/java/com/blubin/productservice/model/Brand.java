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
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id",updatable = false, nullable = false)
    private UUID id;

    @Size(max = 200)
    @NotNull
    @Column(name = "brand_name", nullable = false, length = 200)
    private String brandName;

    @Column(name = "brand_description", length = Integer.MAX_VALUE)
    private String brandDescription;

}