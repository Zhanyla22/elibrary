package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UserDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.auth.RegistrationRequest;
import com.example.neolabs.dto.request.update.UpdateUserRequest;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.impl.CsvExportServiceImpl;
import com.example.neolabs.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin Resource", description = "The Admin API ")
public class AdminController extends BaseController {

    private final UserServiceImpl userService;

    private final CsvExportServiceImpl csvExportService;

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"users.csv\"");
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    @Operation(summary = "Регистрация нового пользователя   || Саку")
    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> emergencyRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return constructSuccessResponse(userService.registration(registrationRequest));
    }

    @Operation(summary = "удаление пользователя по Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return constructSuccessResponse("User with id " + id + "successfully deleted");
    }

    @Operation(summary = "обновление данных пользователя")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateProfilePageUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateProfilePage(id, updateUserRequest);
        return constructSuccessResponse("profile info successfully updated");
    }

    @GetMapping("/users/filter")
    public ResponseEntity<List<UserDto>> filterUsers(@RequestParam("status") Optional<Status> status,
                                                     @RequestParam("role") Optional<Role> role) {
        return ResponseEntity.ok(userService.filter(status.orElse(null), role.orElse(null)));
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam("email") Optional<String> email,
                                                     @RequestParam("firstName") Optional<String> firstName,
                                                     @RequestParam("lastName") Optional<String> lastName,
                                                     @RequestParam("firstOrLastName") Optional<String> firstOrLastName,
                                                     @RequestParam("phoneNumber") Optional<String> phoneNumber) {
        return ResponseEntity.ok(userService.search(email.orElse(null), firstName.orElse(null),
                lastName.orElse(null), firstOrLastName.orElse(null), phoneNumber.orElse(null)));
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam("sortBy") Optional<String> sortBy,
                                                     @RequestParam("size") Optional<Integer> size,
                                                     @RequestParam("page") Optional<Integer> page) {

        return ResponseEntity.ok(userService.getAllUsers(
                PageRequest.of(page.orElse(0), size.orElse(1000), Sort.by(sortBy.orElse("id")))
        ));
    }


    @Operation(summary = "получение 1го пользователя по айди")
    @GetMapping("users/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long id) {
        return constructSuccessResponse(userService.getUserById(id));
    }

    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveUserById(@RequestParam("userId") Long userId,
                                                       @RequestParam(value = "blacklist", defaultValue = "1") Boolean isBlacklist,
                                                       @RequestBody ArchiveRequest archiveRequest) {
        return ResponseEntity.ok(userService.archiveUserById(userId, archiveRequest, isBlacklist));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ResponseDto> unarchiveUserById(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(userService.unarchiveUserById(userId));
    }
}
