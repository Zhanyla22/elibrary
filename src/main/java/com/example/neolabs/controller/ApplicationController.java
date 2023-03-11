package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/applications", produces = "application/json")
@RequiredArgsConstructor
public class ApplicationController extends BaseController {

    private final ApplicationServiceImpl applicationService;

    // TODO: 05.03.2023 need to redo all of the endpoints with constructSuccessResponse()

    @GetMapping("")
    public ResponseEntity<List<ApplicationDto>> getAllApplications(
            @RequestParam(name = "include_archived", defaultValue = "1") boolean includeArchived){
        return ResponseEntity.ok(applicationService.getAllApplications(includeArchived));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("applicationId") Long applicationId){
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> insertApplication(@RequestBody ApplicationDto applicationDto){
        return ResponseEntity.ok(applicationService.insertApplication(applicationDto));
    }
}
