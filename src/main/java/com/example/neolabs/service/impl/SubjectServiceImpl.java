package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.AddSubjectRequest;
import com.example.neolabs.dto.response.SubjectListResponse;
import com.example.neolabs.entity.Subject;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.SubjectListMapper;
import com.example.neolabs.repository.SubjectRepository;
import com.example.neolabs.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectListResponse> findAllByStatus() {
        return SubjectListMapper.entityListToDtoList(subjectRepository.findAllByStatus(Status.ACTIVE));
    }

    @Override
    public Subject findById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(
                ()-> new BaseException("subject with id "+id+" not found", HttpStatus.NOT_FOUND)
        );
        return subject;
    }

    @Override
    public void addNewSubject(AddSubjectRequest addSubjectRequest) {
        if(!subjectRepository.existsByName(addSubjectRequest.getName()))
        subjectRepository.save(SubjectListMapper.dtoToEntity(addSubjectRequest));
        else throw new BaseException("already excits",HttpStatus.NOT_ACCEPTABLE);
    }
}
