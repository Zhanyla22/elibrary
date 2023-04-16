package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.update.UpdateMentorRequest;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.MentorService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @Operation(summary = "Get all mentors cards / получение всех карточек менторов")
    @GetMapping("/cards")
    public ResponseEntity<ResponseDto> getAllMentorCard(@RequestParam("courseId") Optional<Long> courseId,
                                                        @RequestParam("status") Optional<Status> status) {
        return constructSuccessResponse(mentorService.getAllMentorCards(courseId.orElse(null), status.orElse(null)));
    }

    @Operation(summary = "Get mentor by id")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getMentorById(@PathVariable Long id) {
        return constructSuccessResponse(mentorService.getMentorById(id));
    }

    @Operation(summary = "Add new mentor")
    @PostMapping("")
    public ResponseEntity<ResponseDto> addNewMentor(@RequestBody CreateMentorRequest createMentorRequest) {
        mentorService.addNewMentor(createMentorRequest);
        return constructSuccessResponse("created");
    }

    @Hidden // FIXME: 16.04.2023 redundant
    @Operation(summary = "delete mentor by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentorById(id);
        return constructSuccessResponse("deleted");
    }

    @Operation(summary = "update mentor by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateMentor(@PathVariable Long mentorId,
                                                    @RequestBody @Valid UpdateMentorRequest updateMentorRequest) {
        mentorService.updateMentorById(updateMentorRequest, mentorId);
        return constructSuccessResponse("updated");
    }

    @Operation(summary = "archive mentors by id")
    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveMentorById(@RequestParam("mentorId") Long mentorId,
                                  @RequestParam(name = "blacklist", defaultValue = "0") boolean isBlacklist,
                                  @RequestBody @Valid ArchiveRequest archiveRequest) {
        return ResponseEntity.ok(mentorService.archiveMentorById(mentorId, archiveRequest, isBlacklist));
    }

    // TODO: 16.04.2023
//    @Operation(summary = "blacklist mentors by id")
//    @PutMapping("/blacklist")
//    public void blackListMentorById(@RequestParam("mentorId") Long mentorId,
//                                    @RequestBody @Valid ArchiveRequest archiveRequest) {
//        mentorService.blackListMentorById(mentorId, archiveRequest);
//    }
}
