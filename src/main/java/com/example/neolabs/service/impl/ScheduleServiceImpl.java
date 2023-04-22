package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.ScheduleDto;
import com.example.neolabs.dto.request.create.CreateScheduleRequest;
import com.example.neolabs.dto.request.update.UpdateScheduleRequest;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Schedule;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ScheduleMapper;
import com.example.neolabs.repository.ScheduleRepository;
import com.example.neolabs.service.ScheduleService;
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.ResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleServiceImpl implements ScheduleService {

    final GroupServiceImpl groupService;
    final ScheduleRepository scheduleRepository;

    @Override
    public ResponseDto createScheduleForAGroup(CreateScheduleRequest createScheduleRequest) {
        Schedule schedule = new Schedule();
        Group group = groupService.getGroupEntityById(createScheduleRequest.getGroupId());
        schedule.setGroup(group);
        schedule.setDayOfTheWeek(createScheduleRequest.getDayOfTheWeek());
        schedule.setStartTime(LocalTime.parse(createScheduleRequest.getStartTime()));
        schedule.setEndTime(LocalTime.parse(createScheduleRequest.getEndTime()));
        schedule.setCreatedDate(LocalDateTime.now(DateUtil.getZoneId()));
        scheduleRepository.save(schedule);
        return ResponseUtil.buildSuccessResponse("Schedule is created successfully");
    }

    @Override
    public List<ScheduleDto> getScheduleByGroupId(Long groupId){
        return ScheduleMapper.entityListToDtoList(scheduleRepository.findThreeSchedulesByGroupId(groupId));
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return ScheduleMapper.scheduleToScheduleDto(getScheduleEntityById(id));
    }

    @Override
    public Schedule getScheduleEntityById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.SCHEDULE, "id", id);
        });
    }

    @Override
    public ResponseDto updateScheduleById(Long id, UpdateScheduleRequest updateScheduleRequest){
        Schedule schedule = getScheduleEntityById(id);
        schedule.setDayOfTheWeek(updateScheduleRequest.getDayOfTheWeek());
        schedule.setStartTime(LocalTime.parse(updateScheduleRequest.getStartTime()));
        schedule.setEndTime(LocalTime.parse(updateScheduleRequest.getEndTime()));
        schedule.setUpdatedDate(LocalDateTime.now(DateUtil.getZoneId()));
        scheduleRepository.save(schedule);
        return ResponseUtil.buildSuccessResponse("Schedule is updated successfully");
    }

    @Override
    public ResponseDto deleteScheduleById(Long id){
        Schedule schedule = getScheduleEntityById(id);
        scheduleRepository.delete(schedule);
        return ResponseUtil.buildSuccessResponse("Schedule is deleted successfully");
    }
}
