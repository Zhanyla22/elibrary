package com.example.neolabs.controller;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
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
    public ResponseEntity<ResponseDto> insertGroup(@RequestBody CreateGroupRequest createGroupRequest){
        return ResponseEntity.ok(groupService.insertGroup(createGroupRequest));
    }

    @Operation(summary = "Get group by id")
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(groupService.getGroupById(groupId));
    }

    @Operation(summary = "Update group by id")
    @PutMapping(value = {"/{groupId}"})
    public ResponseEntity<GroupDto> updateGroupById(@PathVariable("groupId") Long groupId,
                                                    @Valid @RequestBody CreateGroupRequest createGroupRequest){
        return ResponseEntity.ok(groupService.updateGroupById(groupId, createGroupRequest));
    }

    @Operation(summary = "Archive group by id")
    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveGroupById(@RequestParam("groupId") Long groupId,
                                 @RequestBody ArchiveRequest archiveRequest) {
        return ResponseEntity.ok(groupService.archiveGroupById(groupId, archiveRequest));
    }

    @Operation(summary = "Archive group by id")
    @PutMapping("/unarchive")
    public ResponseEntity<ResponseDto> archiveGroupById(@RequestParam("groupId") Long groupId) {
        return ResponseEntity.ok(groupService.unarchiveGroupById(groupId));
    }
}
