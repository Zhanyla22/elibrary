package com.example.neolabs.service;

import com.example.neolabs.dto.request.CodeRequest;
import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.request.ForgotPasswordCodeRequest;
import com.example.neolabs.dto.request.ForgotPasswordRequest;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.request.UpdatePasswordRequest;
import com.example.neolabs.dto.request.UpdateUserRequest;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;


public interface UserService {

    ResponseDto registration(RegistrationRequest registrationRequest);

    AuthResponse2RoleResponse auth(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refreshToken(User user);

    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws MessagingException;

    String codeGenerate();

    void confirmCode(ForgotPasswordCodeRequest forgotPasswordCodeRequest);

    void confirmCodeFirst(CodeRequest code);

    void updateProfilePage(Long id, UpdateUserRequest updateUserRequest);

    void updateProfileImage(MultipartFile multipartFile, User user);

    void deleteUserById(Long id);

    List<UserResponse> search(String email, String firstName, String lastName, String firstOrLastName, String phoneNumber);

    List<UserResponse> filter(Status status, Role role);

    List<UserCountResponse> findAllByStatus(int skip, int limit);

    UserResponse getUserById(Long id);

    List<SavedResponse> getAllSaved(User user);

    List<UserBookingInfoResponse> getALlBooked(User user, int skip, int limit);

    UserInfo currentUser(User user);

    AllUser getCountOfUsers();



}
