package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.request.AuthenticationRequest;
import com.example.neolabs.dto.response.ResponseDto;
import com.example.neolabs.entity.User;
import com.example.neolabs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Авторизация", description = "API Авторизация")
public class AuthController extends BaseController {

    private final UserService userService;

    @PostMapping("/sign-in")
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
}
