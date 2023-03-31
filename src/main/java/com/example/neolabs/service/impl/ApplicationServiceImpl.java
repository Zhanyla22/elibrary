package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.repository.ApplicationRepository;
import com.example.neolabs.service.ApplicationService;
import com.example.neolabs.util.OperationUtil;
import com.example.neolabs.util.StatusUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationServiceImpl implements ApplicationService {

    final ApplicationMapper applicationMapper;
    final ApplicationRepository applicationRepository;
    final StudentServiceImpl studentService;
    final OperationServiceImpl operationService;
    final OperationUtil opUtil;

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {
        return applicationMapper.entityToDto(getApplicationEntityById(applicationId));
    }

    @Override
    public List<ApplicationDto> getAllApplications(Boolean isArchived, PageRequest pageRequest) {
        Page<Application> applications;
        if (isArchived != null) {
            applications = applicationRepository.findAllByIsArchived(isArchived, pageRequest);
        } else {
            applications = applicationRepository.findAll(pageRequest);
        }
        return applicationMapper.entityListToDtoList(applications.stream().toList());
    }

    @Override
    public ResponseDto updateApplicationStatus(Long applicationId, Integer newStatus) {
        Application application = getApplicationEntityById(applicationId);
        ApplicationStatus oldStatus = application.getApplicationStatus();
        application.setApplicationStatus(StatusUtil.getApplicationStatus(newStatus));
        application.setIsArchived(newStatus > 4);
        operationService.recordApplicationOperation(applicationRepository.save(application), OperationType.UPDATE);
        return ResponseDto.builder()
                .result("Application status has been successfully updated from "
                        + oldStatus.toString() + " to " + application.getApplicationStatus().toString() + ".")
                .build();

        // TODO: 12.03.2023 need to add some new entity like "ApplicationHistory" for analytics
    }

    @Override
    public ResponseDto updateApplicationById(Long applicationId, ApplicationDto applicationDto) {
        getApplicationEntityById(applicationId);
        Application application = applicationMapper.dtoToEntity(applicationDto);
        application.setId(applicationId);
        operationService.recordApplicationOperation(applicationRepository.save(application), OperationType.UPDATE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with " + applicationId + " has been successfully updated.")
                .build();
    }

    @Override
    public ResponseDto insertApplication(ApplicationDto applicationDto) {
        Application application = applicationRepository.save(applicationMapper.dtoToEntity(applicationDto));
        if (application.getApplicationStatus() == null) {
            application.setApplicationStatus(ApplicationStatus.WAITING_FOR_CALL);
        }
        ApplicationDto newDto = applicationMapper.entityToDto(application);
        operationService.recordApplicationOperation(application, OperationType.CREATE);
        return ResponseDto.builder()
                .result(newDto)
                .resultCode(ResultCode.SUCCESS)
                .build();
    }

    @Override
    public ResponseDto deleteApplicationById(Long applicationId) {
        // FIXME: 12.03.2023 need to discuss whether hard deletion needed or not
        return null;
    }

    @Override
    public ResponseDto archiveApplicationById(Long applicationId, ArchiveRequest archiveRequest) {
        Application application = getApplicationEntityById(applicationId);
        application.setIsArchived(true);
        application.setApplicationStatus(ApplicationStatus.DID_NOT_APPLY_FOR_COURSES);
        application.setArchiveReason(archiveRequest.getReason());
        operationService.recordApplicationOperation(applicationRepository.save(application), OperationType.ARCHIVE);
        // TODO: 12.03.2023 need to save last status before archiving for analytics
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with id " + applicationId + " has been successfully archived.")
                .build();
    }

    @Override
    public ResponseDto unarchiveApplicationById(Long applicationId) {
        Application application = getApplicationEntityById(applicationId);
        application.setIsArchived(false);
        operationService.recordApplicationOperation(applicationRepository.save(application), OperationType.UNARCHIVE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with id " + applicationId + " has been successfully unarchived.")
                .build();
        // TODO: 12.03.2023 same as function above
    }

    @Override
    public ResponseDto convertApplication(ConversionRequest conversionRequest) {
        studentService.insertStudentFromApplication(getApplicationEntityById(conversionRequest.getApplicationId()),
                conversionRequest);
        return ResponseDto.builder()
                .result("Successfully converted application into a student.")
                .resultCode(ResultCode.SUCCESS)
                .build();
        // TODO: 15.03.2023 need operations and some analytics 
    }

    @Override
    public Application getApplicationEntityById(Long applicationId) {
        return applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.APPLICATION, "id", applicationId);
        });
    }

}
