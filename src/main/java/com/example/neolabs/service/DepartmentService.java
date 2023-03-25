package com.example.neolabs.service;

import com.example.neolabs.dto.DepartmentDto;
import com.example.neolabs.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto insertDepartment(DepartmentDto departmentDTO);

    DepartmentDto deleteDepartmentById(Long id);

    Department getDepartmentEntityById(Long id);

    DepartmentDto updateDepartmentById(Long id, DepartmentDto departmentDTO);
}
