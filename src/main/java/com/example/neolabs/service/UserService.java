package com.example.neolabs.service;

import com.example.neolabs.entity.User;
import com.example.neolabs.security.request.AuthenticationRequest;
import com.example.neolabs.security.response.AuthenticationResponse;
import com.example.neolabs.security.request.RegistrationRequest;
import com.example.neolabs.security.response.RegistrationResponse;

public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticationResponse auth(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refreshToken(User user);
}
