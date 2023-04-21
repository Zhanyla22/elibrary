package com.example.neolabs.mapper;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.dto.request.create.CreateApplicationRequest;
import com.example.neolabs.dto.request.update.UpdateApplicationRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Student;
import com.example.neolabs.service.impl.GroupServiceImpl;
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.StatusUtil;
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

    public ApplicationDto entityToDto(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .email(application.getEmail())
                .gender(application.getGender().getRussian())
                .applicationStatus(application.getApplicationStatus())
                .applicationStatusNum(application.getApplicationStatus().getOrder())
                .applicationStatusUpdateDate(DateUtil.datetimeToDateFormatter.format(application.getApplicationStatusUpdateDate()))
                .applicationStatusUpdateTime(DateUtil.datetimeFormatter.format(application.getApplicationStatusUpdateDate()))
                .creationDate(DateUtil.datetimeToDateFormatter.format(application.getCreatedDate()))
                .updateDate(application.getUpdatedDate() != null ?
                        DateUtil.datetimeToDateFormatter.format(application.getUpdatedDate()) : null)
                .education(application.getEducation())
                .isArchived(application.getIsArchived())
                .isUrgent(DateUtil.findDifference(LocalDateTime.now(),
                        application.getApplicationStatusUpdateDate(), ChronoUnit.SECONDS) > 86400)
                .reason(application.getReason())
                .phoneNumber(application.getPhoneNumber())
                .hasLaptop(application.getHasLaptop())
                .marketingStrategyEnum(application.getMarketingStrategyEnum())
                .courseCardDto(CourseMapper.entityToCardDto(application.getCourse()))
                .build();
    }

    public List<ApplicationDto> entityListToDtoList(List<Application> applications) {
        return applications.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Student entityToStudentEntity(Application application, ConversionRequest conversionRequest) {
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

    public Application createRequestToEntity(CreateApplicationRequest request){
        return Application.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .education(request.getEducation())
                .gender(request.getGender())
                .applicationStatus(request.getApplicationStatusInitialNum() != null ?
                        StatusUtil.getApplicationStatus(request.getApplicationStatusInitialNum()) : null)
                .hasLaptop(request.getHasLaptop())
                .marketingStrategyEnum(request.getMarketingStrategyEnum())
                .phoneNumber(request.getPhoneNumber())
                .reason(request.getReason())
                .build();
    }

    public Application updateEntityWithUpdateRequest(Application application, UpdateApplicationRequest request){
        application.setEducation(request.getEducation() != null ? request.getEducation() : application.getEducation());
        application.setFirstName(request.getFirstName() != null ? request.getFirstName() : application.getFirstName());
        application.setLastName(request.getLastName() != null ? request.getLastName() : application.getLastName());
        application.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : application.getPhoneNumber());
        application.setEmail(request.getEmail() != null ? request.getEmail() : application.getEmail());
        application.setHasLaptop(request.getHasLaptop() != null ? request.getHasLaptop() : application.getHasLaptop());
        return application;
    }
}
