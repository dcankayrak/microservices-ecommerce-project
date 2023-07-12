package com.dcankayrak.userservice.service;

import com.dcankayrak.userservice.core.converter.GeneralConverter;
import com.dcankayrak.userservice.dto.request.UserLoginRequestDto;
import com.dcankayrak.userservice.dto.request.UserRegisterRequestDto;
import com.dcankayrak.userservice.dto.response.UserLoginResponseDto;
import com.dcankayrak.userservice.dto.response.VerifyPhoneNumberResponseDto;
import com.dcankayrak.userservice.entity.Role;
import com.dcankayrak.userservice.entity.User;
import com.dcankayrak.userservice.exception.UserException;
import com.dcankayrak.userservice.feign.VerifyPhoneServiceClient;
import com.dcankayrak.userservice.repository.UserRepository;
import com.dcankayrak.userservice.security.JwtService;
import com.dcankayrak.userservice.security.JwtUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final VerifyPhoneServiceClient verifyPhoneService;

    @Transactional
    public UserLoginResponseDto saveUser(UserRegisterRequestDto request) {
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
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        this.userRepository.save(newUser);
        var jwt = jwtService.generateToken(JwtUserDetails.create(newUser));

        return UserLoginResponseDto
                .builder()
                .token(jwt)
                .build();

    }

    private boolean validatePhoneNumber(String phoneNumber){
        VerifyPhoneNumberResponseDto verifyPhoneNumberResponseDto = verifyPhoneService.verifyPhoneNumber("veriphone.p.rapidapi.com","680f983c50msh8d3d56c18fd41f4p193f02jsn7d9aeba7eb32",phoneNumber);
        return verifyPhoneNumberResponseDto.getPhone_valid();
    }

    public User getUserWithId(Long id) {
        User findedUser = this.userRepository.findById(id).orElseThrow(() -> {throw new UserException("Aradığınız kullanıcı bulunamadı!");});
        return findedUser;
    }

    public Boolean isUserExists(Long id) {
        Optional<User> findedUser = this.userRepository.findById(id);
        return findedUser.isPresent();
    }

    public UserLoginResponseDto login(UserLoginRequestDto request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(auth);
        var user= userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("Kullanıcıya ait email bulunamadı."));

        var jwt = jwtService.generateToken(JwtUserDetails.create(user));

        return UserLoginResponseDto
                .builder()
                .token(jwt)
                .userId(user.getId())
                .firstName(user.getFirstName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(this.userRepository
                .findByEmail(username).orElseThrow());
    }
}
