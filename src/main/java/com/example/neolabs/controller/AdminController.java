package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.request.RegistrationRequest;
import com.example.neolabs.dto.request.UpdateUserRequest;
import com.example.neolabs.dto.response.AllUser;
import com.example.neolabs.dto.response.ResponseDto;
import com.example.neolabs.dto.response.UserCountResponse;
import com.example.neolabs.dto.response.UserResponse;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.impl.CsvExportServiceImpl;
import com.example.neolabs.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(name = "Админ", description = "API Админ ")
public class AdminController extends BaseController {

    private final UserServiceImpl userService;

    private final CsvExportServiceImpl csvExportService;

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"users.csv\"");
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Регистрация нового пользователя   || Саку")
    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> emergencyRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return constructSuccessResponse(userService.registration(registrationRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "удаление пользователя по Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return constructSuccessResponse("User with id " + id + "successfully deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "обновление данных пользователя")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateProfilePageUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateProfilePage(id, updateUserRequest);
        return constructSuccessResponse("profile info successfully updated");
    }

    @GetMapping("/users/filter")
    public ResponseEntity<List<UserResponse>> filterUsers(@RequestParam("status") Optional<Status> status,
                                                          @RequestParam("role") Optional<Role> role) {
        return ResponseEntity.ok(userService.filter(status.orElse(null), role.orElse(null)));
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam("email") Optional<String> email,
                                                          @RequestParam("firstName") Optional<String> firstName,
                                                          @RequestParam("lastName") Optional<String> lastName,
                                                          @RequestParam("firstOrLastName") Optional<String> firstOrLastName,
                                                          @RequestParam("phoneNumber") Optional<String> phoneNumber) {
        return ResponseEntity.ok(userService.search(email.orElse(null), firstName.orElse(null),
                lastName.orElse(null), firstOrLastName.orElse(null), phoneNumber.orElse(null)));
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/users/{skip}/{limit}")
    public List<UserCountResponse> getAllUsers(@PathVariable int skip,
                                               @PathVariable int limit) {

        return userService.findAllByStatus(skip,limit);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "получение 1го пользователя по айди")
    @GetMapping("users/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long id) {
        return constructSuccessResponse(userService.getUserById(id));
    }

    @Operation(summary = "аналитика - количество пользователей")
    @GetMapping("/count")
    private AllUser allUserCount(){
        return userService.getCountOfUsers();
    }
}
