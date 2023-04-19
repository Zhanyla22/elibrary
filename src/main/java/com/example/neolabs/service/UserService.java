package com.example.neolabs.service;

import com.example.neolabs.dto.request.*;
import com.example.neolabs.dto.request.auth.AuthenticationRequest;
import com.example.neolabs.dto.request.auth.ForgotPasswordCodeRequestDto;
import com.example.neolabs.dto.request.auth.ForgotPasswordRequestDto;
import com.example.neolabs.dto.request.auth.RegistrationRequest;
import com.example.neolabs.dto.request.update.UpdatePasswordRequest;
import com.example.neolabs.dto.request.update.UpdateUserRequest;
import com.example.neolabs.dto.*;
import com.example.neolabs.dto.response.AuthResponse2Role;
import com.example.neolabs.entity.User;
import com.example.neolabs.dto.response.AuthenticationResponse;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;


public interface UserService {

    ResponseDto registration(RegistrationRequest registrationRequest);

    AuthResponse2Role auth(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refreshToken(User user);

    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) throws MessagingException;

    String codeGenerate();

    void confirmCode(ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto);

    void confirmCodeFirst(CodeRequest code);

    void updateProfilePage(Long id, UpdateUserRequest updateUserRequest);

    void updateProfileImage(MultipartFile multipartFile, User user);

    void deleteUserById(Long id);

    List<UserDto> search(String email, String firstName, String lastName, String firstOrLastName, String phoneNumber);

    List<UserDto> filter(Status status, Role role);

    List<UserDto> getAllUsers(PageRequest pageRequest);

    UserDto getUserById(Long id);

    ResponseDto archiveUserById(Long userId, ArchiveRequest archiveRequest, Boolean isBlacklist);

    ResponseDto unarchiveUserById(Long userId);

    List<User> getBlacklist();
}
