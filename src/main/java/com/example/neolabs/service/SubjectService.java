package com.example.neolabs.service;

import com.example.neolabs.dto.request.AddSubjectRequest;
import com.example.neolabs.dto.response.SubjectListResponse;
import com.example.neolabs.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<SubjectListResponse> findAllByStatus();

    Subject findById(Long id);

    void addNewSubject(AddSubjectRequest addSubjectRequest);

}
