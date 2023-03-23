package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.request.UpdateUserRequest;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.CsvExportService;
import com.example.neolabs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin Resource", description = "The Admin API ")
public class AdminController extends BaseController {

    private final UserService userService;

    private final CsvExportService csvExportService;

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"users.csv\"");
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    @PostMapping("/registration")
    @Operation(summary = "User registration/ добавление пользователя только для админа")
    public ResponseEntity<ResponseDto> registration(@RequestBody RegistrationRequest registrationRequest, MultipartFile multipartFile) {
        return constructSuccessResponse(
                userService.registration(registrationRequest, multipartFile)
        );
    }

    @Operation(summary = "удаление пользователя по Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return constructSuccessResponse("User with id " + id + "successfully deleted");
    }

    @Operation(summary = "обновление данных пользователя - для админов - есть и роль")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateProfilePageUser(@PathVariable Long id,@RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateProfilePageWithRole(id,updateUserRequest);
        return constructSuccessResponse("profile info successfully updated");
    }

    @Operation(summary = "получение всех пользователей по статусу")
    @GetMapping("/all-user/{status}")
    public ResponseEntity<ResponseDto> getAllUserByStatus(@PathVariable Status status) {
        userService.getAllUserByStatus(status);
        return constructSuccessResponse(userService.getAllUserByStatus(status));
    }

    //TODO: исправить
    @Operation(summary = "получение 1го пользователя по айди и статус")
    @GetMapping("user/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long id) {
        return  constructSuccessResponse(userService.getUserById(id));
    }
}
