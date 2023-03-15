package com.example.neolabs.service;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface StudentService {
    ResponseDto insertStudent(StudentDto studentDto);
    void insertStudentFromApplication(Application application, ConversionRequest conversionRequest);
    List<StudentDto> getAllStudents(Boolean includeArchived, PageRequest pageRequest);
    StudentDto getStudentById(Long studentId);
    ResponseDto updateStudentById(Long studentId, StudentDto studentDto);
    ResponseDto archiveStudentById(Long studentId, ArchiveRequest archiveRequest);
    ResponseDto unarchiveStudentById(Long studentId);
}
