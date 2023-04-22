package com.example.neolabs.service;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.ScheduleDto;
import com.example.neolabs.dto.request.create.CreateScheduleRequest;
import com.example.neolabs.dto.request.update.UpdateScheduleRequest;
import com.example.neolabs.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ResponseDto createScheduleForAGroup(CreateScheduleRequest createScheduleRequest);

    List<ScheduleDto> getScheduleByGroupId(Long groupId);

    ScheduleDto getScheduleById(Long id);

    Schedule getScheduleEntityById(Long id);

    ResponseDto updateScheduleById(Long id, UpdateScheduleRequest updateScheduleRequest);

    ResponseDto deleteScheduleById(Long id);
}
