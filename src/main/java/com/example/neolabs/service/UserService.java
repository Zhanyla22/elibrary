package com.example.neolabs.service;

import com.example.neolabs.dto.authentication.AuthenticationRequest;
import com.example.neolabs.dto.authentication.AuthenticationResponse;
import com.example.neolabs.dto.registration.RegistrationRequest;
import com.example.neolabs.dto.registration.RegistrationResponse;

public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticationResponse auth(AuthenticationRequest authenticationRequest);


}
