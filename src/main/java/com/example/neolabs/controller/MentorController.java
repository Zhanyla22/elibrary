package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.UpdateMentorDto;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mentors")
@Tag(name = "Mentor Resource", description = "The Mentor API ")
public class MentorController extends BaseController {

    final MentorService mentorService;

    @Operation(summary = "get all mentors cards / получение всех карточек менторов")
    @GetMapping("/all-cards")
    public ResponseEntity<ResponseDto> getAllMentorCard(@RequestParam("courseId") Optional<Long> courseId,
                                                        @RequestParam("status") Optional<Status> status) {
        return constructSuccessResponse(mentorService.getAllMentorCards(courseId.orElse(null), status.orElse(null)));
    }

    @Operation(summary = "get  mentor by id")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getMentorById(@PathVariable Long id) {
        return constructSuccessResponse(mentorService.getMentorById(id));
    }

    @Operation(summary = "add new mentor")
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNewMentor(@RequestBody CreateMentorRequest createMentorRequest) {
        mentorService.addNewMentor(createMentorRequest);
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
