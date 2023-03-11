package com.example.neolabs.service;

import com.example.neolabs.dto.DepartmentDTO;
import com.example.neolabs.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO insertDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO deleteDepartmentById(Long id);
    Department getDepartmentEntityById(Long id);
    DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO);
}
