package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.ScheduleDto;
import com.example.neolabs.dto.request.create.CreateScheduleRequest;
import com.example.neolabs.dto.request.update.UpdateScheduleRequest;
import com.example.neolabs.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
@Tag(name = "Schedule Resource", description = "The Schedule API ")
public class ScheduleController extends BaseController {

    final ScheduleService scheduleService;

    @Operation(summary = "Create schedule for a group")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSchedulesForAGroup(@RequestBody CreateScheduleRequest createScheduleRequest){
        return ResponseEntity.ok(scheduleService.createScheduleForAGroup(createScheduleRequest));
    }

    @Operation(summary = "Get all schedules by group Id")
    @GetMapping("{groupId}")
    public ResponseEntity<List<ScheduleDto>> getSchedulesByGroupId(@PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(scheduleService.getScheduleByGroupId(groupId));
    }

    @Operation(summary = "Get schedule by id")
    @GetMapping("{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable("id") Long id){
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @Operation(summary = "Update schedule by id")
    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateScheduleById(@PathVariable("id") Long id,
                                                          @RequestBody UpdateScheduleRequest updateScheduleRequest){
        return ResponseEntity.ok(scheduleService.updateScheduleById(id,updateScheduleRequest));
    }

    @Operation(summary = "Delete schedule by id")
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteScheduleById(@PathVariable("id") Long id){
        return ResponseEntity.ok(scheduleService.deleteScheduleById(id));
    }
}