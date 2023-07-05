package com.dcankayrak.userservice.service;

import com.dcankayrak.userservice.core.converter.GeneralConverter;
import com.dcankayrak.userservice.dto.request.UserRegisterRequestDto;
import com.dcankayrak.userservice.dto.response.VerifyPhoneNumberResponseDto;
import com.dcankayrak.userservice.entity.User;
import com.dcankayrak.userservice.exception.UserException;
import com.dcankayrak.userservice.feign.VerifyPhoneServiceClient;
import com.dcankayrak.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GeneralConverter generalConverter;
    private final VerifyPhoneServiceClient verifyPhoneService;

    @Transactional
    public void saveUser(UserRegisterRequestDto request) {
        if(!validatePhoneNumber(request.getPhoneNumber())){
            throw new UserException("Kullanıcının telefon numarası hatalıdır!");
        }
        User newUser = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .nationalityId(request.getNationalityId())
                .phoneNumber(request.getPhoneNumber())
                .build();


        this.userRepository.save(newUser);
    }

    private boolean validatePhoneNumber(String phoneNumber){
        VerifyPhoneNumberResponseDto verifyPhoneNumberResponseDto = verifyPhoneService.verifyPhoneNumber("veriphone.p.rapidapi.com","680f983c50msh8d3d56c18fd41f4p193f02jsn7d9aeba7eb32",phoneNumber);
        return verifyPhoneNumberResponseDto.getPhone_valid();
    }
}
