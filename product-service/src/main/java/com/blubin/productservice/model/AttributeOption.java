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
@Table(name = "attribute_option")
public class AttributeOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_option_id",updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_type_id")
    private AttributeType attributeType;

    @Size(max = 100)
    @NotNull
    @Column(name = "attribute_option_name", nullable = false, length = 100)
    private String attributeOptionName;

}