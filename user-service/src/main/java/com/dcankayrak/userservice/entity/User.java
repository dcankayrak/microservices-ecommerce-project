package com.dcankayrak.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
