package com.blubin.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "colour")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colour_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "colour_name", nullable = false, length = 100)
    private String colourName;

}