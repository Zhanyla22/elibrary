package com.example.neolabs.service;

import com.example.neolabs.dto.ForgotPasswordCodeRequestDto;
import com.example.neolabs.dto.ForgotPasswordRequestDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UpdatePasswordDto;
import com.example.neolabs.entity.User;
import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.response.AuthenticationResponse;
import com.example.neolabs.dto.response.RegistrationResponse;

import javax.mail.MessagingException;


public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticationResponse auth(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refreshToken(User user);

    ResponseDto updatePassword(UpdatePasswordDto updatePasswordDTO);

    void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) throws MessagingException;

    String codeGenerate();

    void confirmCode(ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto);

}
