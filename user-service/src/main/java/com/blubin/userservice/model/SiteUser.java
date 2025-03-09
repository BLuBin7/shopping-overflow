//package com.blubin.userservice.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "site_user", schema = "public", uniqueConstraints = {
//        @UniqueConstraint(name = "site_user_email_address_key", columnNames = {"email_address"})
//})
//public class SiteUser {
//    @Id
//    @Column(name = "id", nullable = false)
//    private UUID id;
//
//    @Size(max = 255)
//    @NotNull
//    @Column(name = "email_address", nullable = false)
//    private String emailAddress;
//
//    @Size(max = 20)
//    @Column(name = "phone_number", length = 20)
//    private String phoneNumber;
//
//    @Size(max = 255)
//    @NotNull
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Size(max = 50)
//    @NotNull
//    @ColumnDefault("'USER'")
//    @Column(name = "role", nullable = false, length = 50)
//    private String role;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "created_at")
//    private Instant createdAt;
//
//}