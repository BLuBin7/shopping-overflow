package com.blubin.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "unit_number", length = 20)
    private String unitNumber;

    @Size(max = 20)
    @Column(name = "street_number", length = 20)
    private String streetNumber;

    @Size(max = 255)
    @NotNull
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Size(max = 255)
    @Column(name = "address_line2")
    private String addressLine2;

    @Size(max = 100)
    @NotNull
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Size(max = 100)
    @Column(name = "region", length = 100)
    private String region;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

}