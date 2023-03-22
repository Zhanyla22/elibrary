package com.example.neolabs.service.impl;

import com.example.neolabs.entity.Group;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.repository.GroupRepository;
import com.example.neolabs.service.GroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupServiceImpl implements GroupService {

    final GroupRepository groupRepository;

    public Group getGroupEntityById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.GROUP, "id", groupId);
        });
    }
}
