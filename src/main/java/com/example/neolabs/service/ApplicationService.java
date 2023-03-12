package com.example.neolabs.service;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.entity.Application;

import java.util.List;

public interface ApplicationService {
    ApplicationDto getApplicationById(Long applicationId);
    List<ApplicationDto> getAllApplications(boolean includeArchived);
    ResponseDto updateApplicationStatus(Long applicationId, Integer newStatus);
    ResponseDto insertApplication(ApplicationDto applicationDto);
    Application getApplicationEntityById(Long applicationId);
    // TODO: 05.03.2023
    // add searching, filters, FlexBe

}
