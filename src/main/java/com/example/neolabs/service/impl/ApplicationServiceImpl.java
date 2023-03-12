package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Entity;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.repository.ApplicationRepository;
import com.example.neolabs.service.ApplicationService;
import com.example.neolabs.util.StatusUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {
        return applicationMapper.entityToDto(getApplicationEntityById(applicationId));
    }

    @Override
    public List<ApplicationDto> getAllApplications(boolean includeArchived) {
        List<Application> applications = includeArchived ?
                applicationRepository.findAll() : applicationRepository.findAllByIsArchived(false);
        return applicationMapper.entityListToDtoList(applications);
    }

    @Override
    public ResponseDto updateApplicationStatus(Long applicationId, Integer newStatus) {
        Application application = getApplicationEntityById(applicationId);
        ApplicationStatus oldStatus = application.getApplicationStatus();
        application.setApplicationStatus(StatusUtil.getApplicationStatus(newStatus));
        application.setIsArchived(newStatus > 4);
        applicationRepository.save(application);
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
        applicationRepository.save(application);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with " + applicationId + " has been successfully updated.")
                .build();
    }

    @Override
    public ResponseDto insertApplication(ApplicationDto applicationDto) {
        ApplicationDto newDto = applicationMapper.entityToDto(
                applicationRepository.save(applicationMapper.dtoToEntity(applicationDto))
        );
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
        application.setApplicationStatus(archiveRequest.getIsBlacklist() ?
                ApplicationStatus.BLACKLISTED : ApplicationStatus.DID_NOT_APPLY_FOR_COURSES);
        // TODO: 12.03.2023 need to save last status before archiving for analytics
        // also need to do something with archive reason
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with id " + applicationId + " has been successfully archived.")
                .build();
    }

    @Override
    public ResponseDto unarchiveApplicationById(Long applicationId){
        Application application = getApplicationEntityById(applicationId);
        application.setIsArchived(false);
        applicationRepository.save(application);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Application with id " + applicationId + " has been successfully archived.")
                .build();
        // TODO: 12.03.2023 same as function above
    }

    @Override
    public Application getApplicationEntityById(Long applicationId) {
        return applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new EntityNotFoundException(Entity.APPLICATION, "id", String.valueOf(applicationId));
        });
    }

}
