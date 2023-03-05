package com.example.neolabs.service.impl;

import com.example.neolabs.dto.DepartmentDTO;
import com.example.neolabs.entity.Department;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.DepartmentMapper;
import com.example.neolabs.repository.DepartmentRepository;
import com.example.neolabs.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
@Tag(name = "Department Resource", description = "The Department API ")
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDTO> getAllDepartments(){
        return departmentMapper.entityListToDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentDTO insertDepartment(DepartmentDTO departmentDTO){
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        return departmentMapper.entityToDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO){
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        department.setId(id);
        return departmentMapper.entityToDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO deleteDepartmentById(Long id){
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

