package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.request.*;
import com.example.neolabs.dto.response.*;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Пользователи", description = "API Пользователь ")
public class UserController extends BaseController {

    private final UserService userService;


    @Operation(summary = "обновление пароля в профиле - старый пароль и новый пароль вместе")
    @PostMapping("/update-password")
    public ResponseEntity<ResponseDto> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        userService.updatePassword(updatePasswordRequest);
        return constructSuccessResponse("Password successfully updated");
    }

    @Operation(summary = "Забыл пароль - отправка почты")
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPass(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws MessagingException {
        userService.forgotPassword(forgotPasswordRequest);
        return constructSuccessResponse("6 digit code sent to your email");
    }

    @Operation(summary = "обновления пароля код+новый пароль")
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ForgotPasswordCodeRequest forgotPasswordCodeRequest) {
        userService.confirmCode(forgotPasswordCodeRequest);
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
    public ResponseEntity<ResponseDto> updateProfilePage(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateProfilePage(null, updateUserRequest);
        return constructSuccessResponse("profile info successfully updated");
    }

    @Operation(summary = "обновление аватара в профиле")
    @PutMapping("/update-avatar")
    public ResponseEntity<ResponseDto> updateProfileImage(@RequestPart MultipartFile multipartFile,
                                                          @AuthenticationPrincipal User user) {
        userService.updateProfileImage(multipartFile, user);
        return constructSuccessResponse("profile image successfully updated");
    }

    @Operation(summary = "все сохраненные книги пользователя")
    @GetMapping("/saved-all")
    public List<SavedResponse> getAllSaved(@AuthenticationPrincipal User user) {
        return userService.getAllSaved(user);
    }

    @Operation(summary = "все забронированные книги пользователя")
    @GetMapping("/booked-all/{skip}/{limit}")
    public List<UserBookingInfoResponse> getAllBooked(@AuthenticationPrincipal User user,
                                                      @PathVariable int skip,
                                                      @PathVariable int limit) {
        return userService.getALlBooked(user, skip, limit);
    }

    @Operation(summary = "информация о текущем пользователе")
    @GetMapping("/me")
    public UserInfo userMe(@AuthenticationPrincipal User user){
        return userService.currentUser(user);
    }

}
