package com.blubin.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_profile", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "user_profile_user_id_key", columnNames = {"user_id"})
})
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id" ,updatable = false , nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Size(max = 100)
    @NotNull
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "profile_avatars", length = Integer.MAX_VALUE)
    private String profileAvatars;

    @NotNull
    @Column(name = "user_id")
    private UUID userId;
}