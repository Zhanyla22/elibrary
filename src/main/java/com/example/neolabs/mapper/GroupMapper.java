package com.example.neolabs.mapper;

import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
import com.example.neolabs.entity.Group;
import com.example.neolabs.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
@RequiredArgsConstructor
public class GroupMapper {

    private final CourseMapper courseMapper;

    public GroupDto entityToDto(Group group){
        return GroupDto.builder()
                .id(group.getId())
                .course(courseMapper.entityToDto(group.getCourse()))
                .maxCapacity(group.getMaxCapacity())
                .mentor(MentorMapper.mentorEntityToMentorCardDto(group.getMentor()))
                .imageUrl(group.getImageUrl())
                .startDate(group.getStartDate())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .build();
    }

    public Group dtoToEntity(GroupDto groupDto){
        return Group.builder()
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(groupDto.getStartDate())
                .endDate(groupDto.getEndDate())
                .status(groupDto.getStatus())
                .build();
    }

    public Group createGroupRequestToEntity(CreateGroupRequest groupDto){
        return Group.builder()
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate((LocalDate) DateUtil.datetimeToDateFormatter.parse(groupDto.getStartDate()))
                .status(groupDto.getStatus())
                .build();
    }

    public List<GroupDto> entityListToDtoList(List<Group> entities){
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
