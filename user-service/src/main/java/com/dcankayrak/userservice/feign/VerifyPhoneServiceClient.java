package com.dcankayrak.userservice.feign;

import com.dcankayrak.userservice.dto.response.VerifyPhoneNumberResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "verifyPhone",url = "https://veriphone.p.rapidapi.com/verify")
public interface VerifyPhoneServiceClient {

    @RequestMapping
    public VerifyPhoneNumberResponseDto verifyPhoneNumber(@RequestHeader("X-RapidAPI-Host") String apiHost, @RequestHeader("X-RapidAPI-Key") String apiKey, @RequestParam String phone);
}
