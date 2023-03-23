package com.example.neolabs.service;

import com.example.neolabs.dto.request.UpdateUserClientRequest;
import com.example.neolabs.dto.request.UpdateUserRequest;
import com.example.neolabs.dto.*;
import com.example.neolabs.entity.User;
import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.response.AuthenticationResponse;
import com.example.neolabs.dto.response.RegistrationResponse;
import com.example.neolabs.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;


public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest, MultipartFile multipartFile);

    AuthenticationResponse auth(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refreshToken(User user);

    void updatePassword(UpdatePasswordDto updatePasswordDTO);

    void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) throws MessagingException;

    String codeGenerate();

    void confirmCode(ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto);

    void confirmCodeFirst(CodeRequest code);

    void updateProfilePageWithRole(Long id,UpdateUserRequest updateUserRequest);

    void updateProfilePage(UpdateUserClientRequest updateUserClientRequest);

    void updateProfileImage(MultipartFile multipartFile, User user);

    void deleteUserById(Long id);

    //TODO: dto
    List<UserDto> getAllUserByStatus(Status status);

    //TODO:DTO
    UserDto getUserById(Long id);

}
