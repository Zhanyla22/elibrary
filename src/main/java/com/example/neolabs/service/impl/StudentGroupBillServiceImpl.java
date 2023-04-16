package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.create.CreateStudentGroupBillRequest;
import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.StudentGroupBillMapper;
import com.example.neolabs.repository.StudentGroupBillRepository;
import com.example.neolabs.service.StudentGroupBillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGroupBillServiceImpl implements StudentGroupBillService {

    final StudentGroupBillRepository studentGroupBillRepository;

    final StudentGroupBillMapper studentGroupBillMapper;

    final GroupServiceImpl groupService;

    final StudentServiceImpl studentService;

    @Override
    public StudentGroupBillDto getStudentGroupBill(Long studentId, Long groupId) {
        return null;
    }

    @Override
    public StudentGroupBill getStudentGroupBillById(Long id) {
        return studentGroupBillRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.STUDENT_GROUP_BILL, "id",id);
        });
    }

    @Override
    public StudentGroupBillDto createStudentGroupBill(CreateStudentGroupBillRequest createStudentGroupBillRequest){
        StudentGroupBill studentGroupBill = new StudentGroupBill();
        studentGroupBill.setStudentGroupDebt(groupService.getGroupEntityById(createStudentGroupBillRequest.getGroupId()).getCourse().getCost() * 1.0);
        studentGroupBill.setGroup(groupService.getGroupEntityById(createStudentGroupBillRequest.getGroupId()));
        studentGroupBill.setStudent(studentService.getStudentEntityById(createStudentGroupBillRequest.getStudentId()));
        studentGroupBill.setCreatedDate(LocalDateTime.now());

        return studentGroupBillMapper.entityToDto(studentGroupBillRepository.save(studentGroupBill));

    }

    @Override
    public StudentGroupBillDto getStudentGroupBillDtoById(Long studentGroupBillId) {
        return studentGroupBillMapper.entityToDto(getStudentGroupBillById(studentGroupBillId));
    }
}
