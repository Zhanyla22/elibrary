package com.example.neolabs.service.impl;

import com.example.neolabs.dto.DepartmentDto;
import com.example.neolabs.entity.Department;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.DepartmentMapper;
import com.example.neolabs.repository.DepartmentRepository;
import com.example.neolabs.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentMapper.entityListToDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentDto insertDepartment(DepartmentDto departmentDTO) {
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        return departmentMapper.entityToDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto updateDepartmentById(Long id, DepartmentDto departmentDTO) {
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        department.setId(id);
        return departmentMapper.entityToDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto deleteDepartmentById(Long id) {
        Department department = getDepartmentEntityById(id);
        departmentRepository.delete(department);
        return departmentMapper.entityToDto(department);
    }

    @Override
    public Department getDepartmentEntityById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> {
            throw new ContentNotFoundException();
        });
    }
}

