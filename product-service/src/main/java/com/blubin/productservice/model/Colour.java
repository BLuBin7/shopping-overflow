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
@Table(name = "colour")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "colour_id",updatable = false, nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "colour_name", nullable = false, length = 100)
    private String colourName;

    @Size(max = 7)
    @NotNull
    @Column(name = "hex_code", nullable = false, length = 7, unique = true)
    private String hexCode;
}