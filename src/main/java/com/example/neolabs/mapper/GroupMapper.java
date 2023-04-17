package com.example.neolabs.mapper;

import com.example.neolabs.dto.GroupStudentsDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
import com.example.neolabs.entity.Group;
import com.example.neolabs.util.DateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {

    public static GroupDto entityToDto(Group group){
        return GroupDto.builder()
                .id(group.getId())
                .course(CourseMapper.entityToDto(group.getCourse()))
                .maxCapacity(group.getMaxCapacity())
                .mentor(MentorMapper.entityToMentorCardDto(group.getMentor()))
                .imageUrl(group.getImageUrl())
                .startDate(group.getStartDate())
                .name(group.getName())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .build();
    }

    public static Group dtoToEntity(GroupDto groupDto){
        return Group.builder()
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(groupDto.getStartDate())
                .endDate(groupDto.getEndDate())
                .status(groupDto.getStatus())
                .build();
    }

    public static Group createGroupRequestToEntity(CreateGroupRequest groupDto){
        return Group.builder()
                .name(groupDto.getName())
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(LocalDate.parse(groupDto.getStartDate(), DateUtil.datetimeToDateFormatter))
                .status(groupDto.getStatus())
                .build();
    }

    public static GroupStudentsDto entityToCardDto(Group group) {
        return GroupStudentsDto.builder()
                .groupId(group.getId())
                .groupName(group.getName())
                .students(StudentMapper.entityListToCardDtoList(group.getStudents()))
                .build();
    }

    public static List<GroupDto> entityListToDtoList(List<Group> entities){
        return entities.stream().map(GroupMapper::entityToDto).collect(Collectors.toList());
    }
}
