package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import com.example.neolabs.service.impl.CsvExportServiceImpl;
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
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Tag(name = "Application Resource", description = "API for Applications")
public class ApplicationController extends BaseController {

    private final ApplicationServiceImpl applicationService;
    private final CsvExportServiceImpl csvExportService;

    @Operation(summary = "Find all Applications",
    description = """
            Can be sorted by:

            - 'id'  (use this instead of 'creationDate')
            
            - 'firstName'
            
            - 'lastName'
            
            - 'email'
            
            Sorting directions (sort_dir possible values):
            
            - ASC (ex. 1-2-3-4-5)
            
            - DESC (ex. 5-4-3-2-1)
            """)
    @GetMapping("")
    public ResponseEntity<List<ApplicationDto>> getAllApplications(
            @RequestParam(name = "include_archived", defaultValue = "1") boolean includeArchived,
            @RequestParam(name = "sort_by")Optional<String> sortBy,
            @RequestParam(name = "sort_dir")Optional<String> sortDirection,
            @RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size){
        Sort.Direction direction = sortDirection.orElse("asc").toLowerCase().equals("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page.orElse(0), size.orElse(20),
                Sort.by(direction, sortBy.orElse("id")));
        return ResponseEntity.ok(applicationService.getAllApplications(includeArchived, pageRequest));
    }

    @Operation(summary = "Find Application by ID")
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("applicationId") Long applicationId){
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    }

    @Operation(summary = "Insert new Application")
    @PostMapping("")
    public ResponseEntity<ResponseDto> insertApplication(@RequestBody ApplicationDto applicationDto){
        return ResponseEntity.ok(applicationService.insertApplication(applicationDto));
    }

    @Operation(summary = "Archive Application by ID")
    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveApplicationById(@RequestParam("id") Long applicationId,
                                                          @RequestBody ArchiveRequest request){
        return ResponseEntity.ok(applicationService.archiveApplicationById(applicationId, request));
    }

    @Operation(summary = "Unarchive Application by ID")
    @PutMapping("/unarchive")
    public ResponseEntity<ResponseDto> unarchiveApplicationById(@RequestParam("id") Long applicationId){
        return ResponseEntity.ok(applicationService.unarchiveApplicationById(applicationId));
    }

    @Operation(summary = "Download CSV sheet of Applications")
    @GetMapping("/csv")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"applications.csv\"");
        csvExportService.writeApplicationsToCsv(servletResponse.getWriter());
    }

    @Operation(summary = "Convert an Application into a new Student")
    @PutMapping("/convert")
    public ResponseEntity<ResponseDto> convertApplicationIntoStudent(@RequestBody ConversionRequest conversionRequest){
        return ResponseEntity.ok(applicationService.convertApplication(conversionRequest));
    }
}
