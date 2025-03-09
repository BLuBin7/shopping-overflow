package com.blubin.paymentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_payment_method")
public class UserPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "user_id")
    private UUID user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @Size(max = 100)
    @Column(name = "provider", length = 100)
    private String provider;

    @Size(max = 50)
    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @ColumnDefault("false")
    @Column(name = "is_default")
    private Boolean isDefault;

}