package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.MentorCardRequestDto;
import com.example.neolabs.entity.Department;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
@Tag(name = "Mentor Resource", description = "The Mentor API ")
public class MentorController extends BaseController {

    final MentorService mentorService;

    @Operation(summary = "get all mentors cards / получение всех карточек менторов")
    @GetMapping("all-cards")
    public ResponseEntity<ResponseDto> getAllMentorCard(@RequestBody MentorCardRequestDto mentorCardRequestDto) {
        return  constructSuccessResponse(mentorService.getAllMentorCard(mentorCardRequestDto));
    }
}
