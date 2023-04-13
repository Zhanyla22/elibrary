package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.entity.Group;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.GroupMapper;
import com.example.neolabs.repository.GroupRepository;
import com.example.neolabs.service.GroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupServiceImpl implements GroupService {

    final GroupRepository groupRepository;
    final GroupMapper groupMapper;

    @Override
    public ResponseDto insertGroup(GroupDto groupDto) {
        Group group = groupMapper.dtoToEntity(groupDto);
        return null;
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupMapper.entityListToDtoList(groupRepository.findAll());
    }

    @Override
    public GroupDto getGroupById(Long groupId) {
        return groupMapper.entityToDto(getGroupEntityById(groupId));
    }

    @Override
    public GroupDto updateGroupById(Long groupId, GroupDto groupDto) {
        Group group = groupMapper.dtoToEntity(groupDto);
        group.setId(groupId);
        return groupMapper.entityToDto(groupRepository.save(group));
    }

    @Override
    public void archiveGroupById(Long groupId, ArchiveDto groupArchiveDto) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new BaseException("group with id " + groupId + " not found", HttpStatus.BAD_REQUEST));
        group.setUpdatedDate(LocalDateTime.now());
        group.setReason(groupArchiveDto.getReason());
        group.setStatus(Status.ARCHIVED);

        groupRepository.save(group);
    }

    @Override
    public void blackListGroupById(Long groupId, ArchiveDto groupBlacklistDto) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new BaseException("group with id " + groupId + " not found", HttpStatus.BAD_REQUEST));
        group.setUpdatedDate(LocalDateTime.now());
        group.setReason(groupBlacklistDto.getReason());
        group.setStatus(Status.BLACK_LIST);

        groupRepository.save(group);
    }

    public Group getGroupEntityById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.GROUP, "id", groupId);
        });
    }
}
