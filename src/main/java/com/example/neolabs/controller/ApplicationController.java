package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import com.example.neolabs.service.impl.CsvExportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController extends BaseController {

    private final ApplicationServiceImpl applicationService;
    private final CsvExportServiceImpl csvExportService;

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

    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveApplicationById(@RequestParam("id") Long applicationId,
                                                          @RequestBody ArchiveRequest request){
        return ResponseEntity.ok(applicationService.archiveApplicationById(applicationId, request));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ResponseDto> unarchiveApplicationById(@RequestParam("id") Long applicationId){
        return ResponseEntity.ok(applicationService.unarchiveApplicationById(applicationId));
    }

    @GetMapping("/csv")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"applications.csv\"");
        csvExportService.writeApplicationsToCsv(servletResponse.getWriter());
    }
}
