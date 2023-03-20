package com.example.neolabs.mapper;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Student;
import com.example.neolabs.service.impl.GroupServiceImpl;
import com.example.neolabs.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    final GroupServiceImpl groupService;

    public ApplicationDto entityToDto(Application application){
        return ApplicationDto.builder()
                .id(application.getId())
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .email(application.getEmail())
                .applicationStatus(application.getApplicationStatus())
                .applicationStatusUpdateDate(application.getApplicationStatusUpdateDate())
                .creationDate(application.getCreatedDate())
                .updateDate(application.getUpdatedDate())
                .education(application.getEducation())
                .isArchived(application.getIsArchived())
                .isUrgent(DateUtil.findDifference(LocalDateTime.now(),
                        application.getApplicationStatusUpdateDate(), ChronoUnit.SECONDS) > 86400)
                .reason(application.getReason())
                .phoneNumber(application.getPhoneNumber())
                .hasLaptop(application.getHasLaptop())
                .marketingStrategy(application.getMarketingStrategy())
                .build();
    }

    public Application dtoToEntity(ApplicationDto applicationDto){
        return Application.builder()
                .firstName(applicationDto.getFirstName())
                .lastName(applicationDto.getLastName())
                .email(applicationDto.getEmail())
                .education(applicationDto.getEducation())
                .applicationStatus(applicationDto.getApplicationStatus())
                .hasLaptop(applicationDto.getHasLaptop())
                .marketingStrategy(applicationDto.getMarketingStrategy())
                .phoneNumber(applicationDto.getPhoneNumber())
                .reason(applicationDto.getReason())
                .build();
    }

    public List<ApplicationDto> entityListToDtoList(List<Application> applications){
        return applications.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    //redundant?
    public List<Application> dtoListToEntityList(List<ApplicationDto> applicationDtos){
        return applicationDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public Student entityToStudentEntity(Application application, ConversionRequest conversionRequest){
        List<Group> groups = new ArrayList<>();
        groups.add(groupService.getGroupEntityById(conversionRequest.getGroupId()));
        return Student.builder()
                .application(application)
                .email(application.getEmail())
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .phoneNumber(application.getPhoneNumber())
                .gender(application.getGender())
                .groups(groups)
                .build();
    }
}