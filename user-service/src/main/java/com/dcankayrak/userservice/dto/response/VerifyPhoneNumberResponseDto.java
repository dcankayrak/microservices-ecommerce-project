package com.dcankayrak.userservice.dto.response;

import lombok.Data;

@Data
public class VerifyPhoneNumberResponseDto {
    private Boolean phone_valid;
    private String country;
    private String county_code;
    private String international_number;
}
