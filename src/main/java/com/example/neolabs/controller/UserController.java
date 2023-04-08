package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.*;
import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.request.UpdateUserClientRequest;
import com.example.neolabs.entity.User;
import com.example.neolabs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User Resource", description = "The User API ")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/auth")
    @Operation(summary = "User authorization/авторизация")
    public ResponseEntity<ResponseDto> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        return constructSuccessResponse(
                userService.auth(authenticationRequest)
        );
    }

    @Operation(summary = "обновление токена с помощью рефреш токена")
    @PostMapping("/refresh")
    public ResponseEntity<ResponseDto> auth(@AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                userService.refreshToken(user)
        );
    }

    @Operation(summary = "обновление пароля в профиле - старый пароль и новый пароль вместе")
    @PostMapping("/update-password")
    public ResponseEntity<ResponseDto> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return constructSuccessResponse("Password successfully updated");
    }

    @Operation(summary = "Забыл пароль - отправка почты")
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPass(@RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) throws MessagingException {
        userService.forgotPassword(forgotPasswordRequestDto);
        return constructSuccessResponse("6 digit code sent to your email");
    }

    @Operation(summary = "обновления пароля код+новый пароль")
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto) {
        userService.confirmCode(forgotPasswordCodeRequestDto);
        return constructSuccessResponse("password successfully changed");
    }

    @Operation(summary = "потвреждение кода")
    @PostMapping("/confirm-code")
    public ResponseEntity<ResponseDto> confirmCodeFirst(@RequestBody CodeRequest code) {
        userService.confirmCodeFirst(code);
        return constructSuccessResponse("code confirmed");
    }

    @Operation(summary = "обновление данных пользователя - для менеджеров")
    @PutMapping("/update-client")
    public ResponseEntity<ResponseDto> updateProfilePage(@RequestBody UpdateUserClientRequest updateUserClientRequest) {
        userService.updateProfilePage(updateUserClientRequest);
        return constructSuccessResponse("profile info successfully updated");
    }

    @Operation(summary = "обновление аватара в профиле")
    @PutMapping("/update-avatar")
    public ResponseEntity<ResponseDto> updateProfileImage(@RequestPart MultipartFile multipartFile,
                                                          @AuthenticationPrincipal User user) {
        userService.updateProfileImage(multipartFile, user);
        return constructSuccessResponse("profile image successfully updated");
    }
}
