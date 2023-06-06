package com.example.neolabs.mapper;

import com.example.neolabs.dto.request.AddSubjectRequest;
import com.example.neolabs.dto.response.SubjectListResponse;
import com.example.neolabs.entity.Subject;
import com.example.neolabs.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectListMapper {

    public static SubjectListResponse entityToDto(Subject subject) {
        return SubjectListResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

    public static List<SubjectListResponse> entityListToDtoList(List<Subject> subjects) {
        return subjects.stream().map(SubjectListMapper::entityToDto).collect(Collectors.toList());
    }

    public static Subject dtoToEntity(AddSubjectRequest addSubjectRequest) {
        return Subject.builder()
                .name(addSubjectRequest.getName())
                .status(Status.ACTIVE)
                .build();
    }

}
