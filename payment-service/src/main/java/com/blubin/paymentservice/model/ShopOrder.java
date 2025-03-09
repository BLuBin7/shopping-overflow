package com.blubin.paymentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id",updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id")
    private UUID user;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

}