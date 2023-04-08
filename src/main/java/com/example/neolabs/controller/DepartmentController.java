package com.example.neolabs.controller;

import com.example.neolabs.dto.DepartmentDto;
import com.example.neolabs.service.impl.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
@Tag(name = "Department Resource", description = "The Department API ")
public class DepartmentController {

    private final DepartmentServiceImpl departmentServiceImpl;

    @Operation(summary = "Get all departments")
    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentServiceImpl.getAllDepartments());
    }


    @Operation(summary = "Insert new department")
    @PostMapping(value = {""}, produces = "application/json")
    public ResponseEntity<DepartmentDto> insertDepartment(@RequestBody DepartmentDto departmentDTO){
        return ResponseEntity.ok(departmentServiceImpl.insertDepartment(departmentDTO));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete department by id")
    public ResponseEntity<DepartmentDto> deleteDepartmentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(departmentServiceImpl.deleteDepartmentById(id));
    }


    @Operation(summary = "Update department by id")
    @PutMapping(value = {"/{id}"})
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable("id") Long id,
                                               @Valid @RequestBody DepartmentDto departmentDTO){
        return ResponseEntity.ok(departmentServiceImpl.updateDepartmentById(id, departmentDTO));
    }
}

