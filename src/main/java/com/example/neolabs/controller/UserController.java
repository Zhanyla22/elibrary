package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ForgotPasswordCodeRequestDto;
import com.example.neolabs.dto.ForgotPasswordRequestDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UpdatePasswordDto;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.security.request.AuthenticationRequest;
import com.example.neolabs.security.request.RegistrationRequest;
import com.example.neolabs.service.CsvExportService;
import com.example.neolabs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "User Resource", description = "The User API ")
public class UserController extends BaseController {
    final UserService userService;

    private final CsvExportService csvExportService;


    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"users.csv\"");
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    @PostMapping("/auth")
    @Operation(summary = "User authorization")
    public ResponseEntity<ResponseDto> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        return constructSuccessResponse(
                userService.auth(authenticationRequest)
        );
    }

    @PostMapping("/registration")
    @Operation(summary = "User registration")
    public ResponseEntity<ResponseDto> registration(@RequestBody RegistrationRequest registrationRequest) {
        return constructSuccessResponse(
                userService.registration(registrationRequest)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDto> auth(@AuthenticationPrincipal User user) {
        return constructSuccessResponse(
                userService.refreshToken(user)
        );
    }

    @PostMapping("/update-password")
    public ResponseEntity<ResponseDto> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDTO){
        userService.updatePassword(updatePasswordDTO);
        return constructSuccessResponse("Password successfully updated");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPass(@RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) throws MessagingException {
        userService.forgotPassword(forgotPasswordRequestDto);
        return constructSuccessResponse("6 digit code sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ForgotPasswordCodeRequestDto forgotPasswordCodeRequestDto){
        userService.confirmCode(forgotPasswordCodeRequestDto);
        return constructSuccessResponse("password successfully changed");
    }

}
