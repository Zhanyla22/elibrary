package com.example.neolabs.controller;

import com.example.neolabs.dto.BlacklistMemberDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.service.impl.ArchiveServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/archive")
@Tag(name = "Archive Resource", description = "The Archive API ")
public class ArchiveController {

    private final ArchiveServiceImpl archiveService;

    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistMemberDto>> getBlacklist(){
        return ResponseEntity.ok(archiveService.getBlacklist());
    }
}
