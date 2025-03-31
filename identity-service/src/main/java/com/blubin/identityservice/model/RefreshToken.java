package com.blubin.identityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiryDate",nullable = false)
    private LocalDateTime expiryDate;

    @CreationTimestamp
    @Column(name = "createdAt" ,nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "revoked",nullable = false)
    private Boolean revoked = false;
}