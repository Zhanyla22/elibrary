package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import com.example.neolabs.service.impl.CsvExportServiceImpl;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applications")
@Tag(name = "Application Resource", description = "The Application API ")
public class ApplicationController extends BaseController {

    private final ApplicationServiceImpl applicationService;
    private final CsvExportServiceImpl csvExportService;

    @GetMapping("")
    public ResponseEntity<List<ApplicationDto>> getAllApplications(
            @RequestParam(name = "include_archived", defaultValue = "1") boolean includeArchived,
            @RequestParam(name = "sort_by")Optional<String> sortBy,
            @RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size){
        PageRequest pageRequest = PageRequest.of(page.orElse(0), size.orElse(20), Sort.by(sortBy.orElse("id")));
        return ResponseEntity.ok(applicationService.getAllApplications(includeArchived, pageRequest));
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

    @PutMapping("/convert")
    public ResponseEntity<ResponseDto> convertApplicationIntoStudent(@RequestBody ConversionRequest conversionRequest){
        return ResponseEntity.ok(applicationService.convertApplication(conversionRequest));
    }
}
