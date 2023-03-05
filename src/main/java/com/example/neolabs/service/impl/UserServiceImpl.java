package com.example.neolabs.service.impl;

import com.example.neolabs.config.securityConfig.JWTService;
import com.example.neolabs.dto.authentication.AuthenticationRequest;
import com.example.neolabs.dto.authentication.AuthenticationResponse;
import com.example.neolabs.dto.registration.RegistrationRequest;
import com.example.neolabs.dto.registration.RegistrationResponse;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.service.UserService;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    final UserRepository userRepository;
    final JWTService jwtService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;

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
            String jwtToken = jwtService.generateToken((User) authenticate.getPrincipal());
            return AuthenticationResponse.builder()
                    .jwtToken(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new BaseException("User not found", HttpStatus.NOT_FOUND);
        }

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

    public Long getCurrentUserId(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new BaseException("User not found", HttpStatus.NOT_FOUND)).getId();
    }
}
