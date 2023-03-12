package com.example.neolabs.mapper;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    public ApplicationDto entityToDto(Application application){
        return ApplicationDto.builder()
                .id(application.getId())
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .email(application.getEmail())
                .applicationStatus(application.getApplicationStatus())
                .applicationStatusUpdateDate(application.getApplicationStatusUpdateDate())
                .creationDate(application.getDateCreated())
                .updateDate(application.getDateUpdated())
                .education(application.getEducation())
                .isArchived(application.getIsArchived())
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
}
