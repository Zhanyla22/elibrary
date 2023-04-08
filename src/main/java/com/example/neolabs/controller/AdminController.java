package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UserDto;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.request.UpdateUserRequest;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.CsvExportService;
import com.example.neolabs.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @Operation(summary = "Регистрация нового пользователя   || Саку")
    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> emergencyRegistration(@RequestBody RegistrationRequest registrationRequest){
        return constructSuccessResponse(userService.registration(registrationRequest));
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
    // TODO: Conversation with Saku
    // why is it divided by status? why is it path variable? and what is this naming "all-user"!? sry, ne uderzhalsya :)
    // 1. Because if I will create endpoint to each status - it will be duplicating(SOLID нарушается) and we have bunch of enum Status
    // 2. I just wanted to practice to put in pathvariable something new instead of id
    // 3. I can make it /all/{status}/user, no problem
    // And I think this endpoint make sense, instead of writing endpoint to each status we can make it in one
    // (this endpoint for filtration)
    @Hidden
    @Operation(summary = "получение всех пользователей по статусу")
    @GetMapping("/all-users/{status}")
    public ResponseEntity<ResponseDto> getAllUserByStatus(@PathVariable Status status) {
        userService.getAllUserByStatus(status);
        return constructSuccessResponse(userService.getAllUserByStatus(status));
    }

    // TODO: 25.03.2023 its Sakubek's code (emergency)
    @Operation(summary = "Получение всех пользователей   || Саку")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    //TODO: исправить
    @Operation(summary = "получение пользователя по айди и статус")
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long id) {
        return  constructSuccessResponse(userService.getUserById(id));
    }
}
