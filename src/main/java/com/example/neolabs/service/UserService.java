package com.example.neolabs.service;

import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.response.response.AuthenticationResponse;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.response.response.RegistrationResponse;

public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticationResponse auth(AuthenticationRequest authenticationRequest);


}
