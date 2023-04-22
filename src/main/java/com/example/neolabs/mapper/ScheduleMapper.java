package com.example.neolabs.mapper;

import com.example.neolabs.dto.ScheduleDto;
import com.example.neolabs.dto.request.create.CreateScheduleRequest;
import com.example.neolabs.entity.Schedule;
import com.example.neolabs.util.DateUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleMapper {

    public static CreateScheduleRequest scheduleToCreateScheduleDto(Schedule schedule){
        return CreateScheduleRequest.builder()
                .groupId(schedule.getGroup().getId())
                .dayOfTheWeek(schedule.getDayOfTheWeek())
                .startTime(schedule.getStartTime() != null ?
                schedule.getStartTime().format(DateUtil.datetimeFormatter) : null)
                .endTime(schedule.getEndTime() != null ?
                        schedule.getEndTime().format(DateUtil.datetimeFormatter) : null)
                .build();
    }

    public static ScheduleDto scheduleToScheduleDto(Schedule schedule){
        return ScheduleDto.builder()
                .id(schedule.getId())
                .groupId(schedule.getGroup().getId())
                .dayOfTheWeek(schedule.getDayOfTheWeek())
                .startTime(schedule.getStartTime() != null ?
                        schedule.getStartTime().format(DateUtil.datetimeFormatter) : null)
                .endTime(schedule.getEndTime() != null ?
                        schedule.getEndTime().format(DateUtil.datetimeFormatter) : null)
                .build();
    }

    public static List<ScheduleDto> entityListToDtoList(List<Schedule> schedule) {
        return schedule.stream().map(ScheduleMapper::scheduleToScheduleDto).collect(Collectors.toList());
    }
}
