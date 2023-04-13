package com.example.neolabs.mapper;

import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.MentorDto;
import com.example.neolabs.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
                //TODO: SAKU LOOK added mentor's info
                .mentor(MentorMapper.mentorEntityToMentorCardDto(group.getMentor()))
                .startDate(group.getStartDate())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .isArchived(group.getIsArchived())
                .build();
    }

    public Group dtoToEntity(GroupDto groupDto){
        return Group.builder()
                .course(courseMapper.dtoToEntity(groupDto.getCourse()))
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(groupDto.getStartDate())
                .endDate(groupDto.getEndDate())
                .status(groupDto.getStatus())
                .isArchived(groupDto.getIsArchived())
                .build();
    }

    public List<GroupDto> entityListToDtoList(List<Group> entities){
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
