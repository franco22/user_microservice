package com.example.usermicroservice.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @NonNull
    private String name;
    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime last_login;

    private UUID token;

    private Boolean isactive;
    @OneToMany(targetEntity = PhoneEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "us_fk",referencedColumnName = "id")
    private List<PhoneEntity> phones;
}
