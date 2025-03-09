package com.blubin.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id",updatable = false, nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotNull
    @Column(name = "country_name", nullable = false)
    private String countryName;
//
//    public void setCountryName(String countryName) {
//        this.countryName = countryName;
//    }
}