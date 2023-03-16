package com.example.neolabs.mapper;

import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.entity.Group;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public GroupDto entityToDto(Group group){
        // TODO: 16.03.2023 Alibek
        return GroupDto.builder().build();
    }

    public List<GroupDto> entityListToDtoList(List<Group> groups){
        return groups.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
