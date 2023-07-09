package com.dcankayrak.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalityId;
    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
}
