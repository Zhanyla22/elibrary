package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.CreateMentorDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UpdateMentorDto;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mentors")
@Tag(name = "Mentor Resource", description = "The Mentor API ")
public class MentorController extends BaseController {

    final MentorService mentorService;

    @Operation(summary = "get all mentors cards / получение всех карточек менторов")
    @GetMapping("/all-cards")
    public ResponseEntity<ResponseDto> getAllMentorCard(@RequestParam("departmentId") Long departmentId,
                                                        @RequestParam("status") Status status) {
        return constructSuccessResponse(mentorService.getAllMentorCard(departmentId, status));
    }

    @Operation(summary = "add new mentor")
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNewMentor(@RequestBody CreateMentorDto createMentorDto) {
        mentorService.addNewMentor(createMentorDto);
        return constructSuccessResponse("created");
    }

    @Operation(summary = "delete mentor by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentorById(id);
        return constructSuccessResponse("deleted");
    }

    @Operation(summary = "update mentor by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateMentor(@PathVariable Long id, @RequestBody UpdateMentorDto updateMentorDto) {
        mentorService.updateMentorById(updateMentorDto, id);
        return constructSuccessResponse("updated");
    }

    @Operation(summary = "archive mentors by id")
    @PutMapping("/archive/{id}")
    public void archiveMentorById(@PathVariable Long id, @RequestBody ArchiveDto mentorArchiveDto) {
        mentorService.archiveMentorById(id, mentorArchiveDto);
    }

    @Operation(summary = "blacklist mentors by id")
    @PutMapping("/blacklist/{id}")
    public void blackListMentorById(@PathVariable Long id, @RequestBody ArchiveDto mentorBlacklistDto) {
        mentorService.blackListMentorById(id, mentorBlacklistDto);
    }
}
