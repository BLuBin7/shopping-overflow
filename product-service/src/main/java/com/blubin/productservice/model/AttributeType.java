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
@Table(name = "attribute_type")
public class AttributeType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_type_id",updatable = false, nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "attribute_name", nullable = false, length = 100)
    private String attributeName;

}