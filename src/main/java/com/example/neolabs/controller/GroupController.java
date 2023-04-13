package com.example.neolabs.controller;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.service.impl.GroupServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/groups")
@Tag(name = "Group Resource", description = "The Group API ")
public class GroupController {

    private final GroupServiceImpl groupService;

    @Operation(summary = "Get all groups")
    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<GroupDto>> getAllGroups(){
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @Operation(summary = "Insert new group")
    @PostMapping(value = {""}, produces = "application/json")
    public ResponseEntity<ResponseDto> insertGroup(@RequestBody GroupDto groupDto){
        return ResponseEntity.ok(groupService.insertGroup(groupDto));
    }

    @Operation(summary = "Get group by id")
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(groupService.getGroupById(groupId));
    }

    @Operation(summary = "Update group by id")
    @PutMapping(value = {"/{groupId}"})
    public ResponseEntity<GroupDto> updateDepartmentById(@PathVariable("groupId") Long groupId,
                                               @Valid @RequestBody GroupDto groupDto){
        return ResponseEntity.ok(groupService.updateGroupById(groupId, groupDto));
    }

    @Operation(summary = "archive group by id")
    @PutMapping("/archive/{id}")
    public void archiveGroupById(@PathVariable Long id, @RequestBody ArchiveDto groupArchiveDto) {
        groupService.archiveGroupById(id, groupArchiveDto);
    }

    @Operation(summary = "blacklist groups by id")
    @PutMapping("/blacklist/{id}")
    public void blackListGroupById(@PathVariable Long id, @RequestBody ArchiveDto groupBlacklistDto) {
        groupService.blackListGroupById(id, groupBlacklistDto);
    }
}
