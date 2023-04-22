package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.ScheduleDto;
import com.example.neolabs.dto.request.create.CreateScheduleRequest;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Schedule;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ScheduleMapper;
import com.example.neolabs.repository.ScheduleRepository;
import com.example.neolabs.service.GroupService;
import com.example.neolabs.service.ScheduleService;
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
        schedule.setCreatedDate(LocalDateTime.now());
        return ResponseDto.builder().build();
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
}
