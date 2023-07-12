package com.dcankayrak.userservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDto {
    private String token;
    private Long userId;
    private String firstName;
}
