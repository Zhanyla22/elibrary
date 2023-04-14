package com.example.neolabs.service;

import com.example.neolabs.dto.ApplicationDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.dto.request.create.CreateApplicationRequest;
import com.example.neolabs.entity.Application;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ApplicationService {

    ApplicationDto getApplicationById(Long applicationId);

    List<ApplicationDto> getAllApplications(Boolean isArchived, PageRequest pageRequest);

    ResponseDto updateApplicationStatus(Long applicationId, Integer newStatus);

    ResponseDto updateApplicationById(Long applicationId, CreateApplicationRequest createApplicationRequest);

    ResponseDto insertApplication(CreateApplicationRequest createApplicationRequest);

    ResponseDto deleteApplicationById(Long applicationId);

    ResponseDto archiveApplicationById(Long applicationId, ArchiveRequest archiveRequest);

    ResponseDto unarchiveApplicationById(Long applicationId);

    ResponseDto convertApplication(ConversionRequest conversionRequest);

    Application getApplicationEntityById(Long applicationId);
    // TODO: 05.03.2023
    // add searching, filters, FlexBe
}
