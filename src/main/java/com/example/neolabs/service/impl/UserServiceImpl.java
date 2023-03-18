package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ForgotPasswordCodeRequestDto;
import com.example.neolabs.dto.ForgotPasswordRequestDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UpdatePasswordDto;
import com.example.neolabs.entity.ResetPassword;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.repository.ResetPasswordRepository;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.security.jwt.JWTService;
import com.example.neolabs.security.request.AuthenticationRequest;
import com.example.neolabs.security.request.RegistrationRequest;
import com.example.neolabs.security.response.AuthenticationResponse;
import com.example.neolabs.security.response.RegistrationResponse;
import com.example.neolabs.service.UserService;
import com.example.neolabs.util.EmailUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepository userRepository;
    final JWTService jwtService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final ResetPasswordRepository resetPasswordRepository;
    final EmailUtil emailUtil;

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        if (!userRepository.existsByEmail(registrationRequest.getEmail())) {
            userRepository.save(User.builder()
                    .email(registrationRequest.getEmail())
                    .firstName(registrationRequest.getFirstName())
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .status(Status.ACTIVE)
                    //TODO:implement image upload
                    .urlImage(registrationRequest.getFirstName())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .role(registrationRequest.getRole())
                    .build());

            return RegistrationResponse.builder()
                    .email(registrationRequest.getEmail())
                    .password(registrationRequest.getPassword())
                    .build();
        } else {
            throw new BaseException("user already exists", HttpStatus.CONFLICT);
        }
    }

    @Override
    public AuthenticationResponse auth(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            return jwtService.generateToken((User) authenticate.getPrincipal());
        } catch (Exception e) {
            throw new BaseException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AuthenticationResponse refreshToken(User user) {
        try {
            return jwtService.generateToken(user);
        } catch (Exception e) {
            throw new BaseException("not found user", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseDto updatePassword(UpdatePasswordDto updatePasswordDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (passwordEncoder.matches(updatePasswordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
            userRepository.save(user);
        }
        return ResponseDto.builder()
                .result(getCurrentUser().getUsername() + " ' s password updated")
                .build();
    }

    @Override
    public void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) { //FIXME: responsDTO на уровне сервиса нельзя
        User users = userRepository.findByEmail(forgotPasswordRequestDto.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));
        String genCode = codeGenerate();
        resetPasswordRepository.save(ResetPassword.builder()
                .code(genCode)
                .dateExpiration(LocalDateTime.now().plus(10, ChronoUnit.MINUTES)) //TODO: how to realize delete after date exp
                .user(users)
                .isActive(false)
                .build());
        emailUtil.send(forgotPasswordRequestDto.getEmail(), "Код для сброса пароля", genCode);
    }

    @Override
    public String codeGenerate() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().replaceAll("-", "");

        return randomUUIDString.substring(0, 6);
    }

    @Override
    public void confirmCode(ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto) {
        ResetPassword resetPassword = resetPasswordRepository
                .findTopByCodeAndIsActiveOrderByDateExpirationDesc(forgotPasswordCodeRequestDto.getCode(), false)
                .orElseThrow(() -> new BaseException("code undefined", HttpStatus.BAD_REQUEST));
        if (resetPassword.getDateExpiration().isAfter(LocalDateTime.now())) {
            User user = resetPassword.getUser();
            user.setPassword(passwordEncoder.encode(forgotPasswordCodeRequestDto.getNewPassword()));
            resetPassword.setIsActive(true);
            resetPasswordRepository.save(resetPassword);
            userRepository.save(user);
        } else throw new BaseException("code date expired", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new BaseException("User not found", HttpStatus.NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return loadUserByUsername(email);
    }

    public Long getCurrentUserId() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new BaseException("User not found", HttpStatus.NOT_FOUND)).getId();
    }
}
