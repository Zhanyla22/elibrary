package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.entity.Application;
import com.example.neolabs.enums.Entity;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.repository.ApplicationRepository;
import com.example.neolabs.service.ApplicationService;
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
        // TODO: 05.03.2023 need to check whether the application is going to archive or not based on new status
        return null;
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
    public Application getApplicationEntityById(Long applicationId) {
        return applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new EntityNotFoundException(Entity.APPLICATION, "id", String.valueOf(applicationId));
        });
    }

}
