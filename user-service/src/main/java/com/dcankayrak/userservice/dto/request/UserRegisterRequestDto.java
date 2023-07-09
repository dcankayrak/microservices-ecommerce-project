package com.dcankayrak.userservice.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalityId;
    private String phoneNumber;
}
