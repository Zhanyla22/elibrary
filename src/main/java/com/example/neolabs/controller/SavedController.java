package com.example.neolabs.controller;


import com.example.neolabs.entity.User;
import com.example.neolabs.service.SavedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saved")
@Tag(name = "Избранные", description = "API Избранные ")
public class SavedController {

    private final SavedService savedService;

    @PostMapping("/save/{bookId}")
    @Operation(summary = "добавить избранные")
    public void saveBook(@AuthenticationPrincipal User user,
                         @PathVariable Long bookId) {
        savedService.save(user, bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteSave(@PathVariable Long bookId){
        savedService.deleteSavedById(bookId);
    }
}
