package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Group;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.GroupMapper;
import com.example.neolabs.repository.GroupRepository;
import com.example.neolabs.service.GroupService;
import com.example.neolabs.util.ResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupServiceImpl implements GroupService {

    final GroupRepository groupRepository;
    final GroupMapper groupMapper;
    final CourseServiceImpl courseService;
    final MentorServiceImpl mentorService;

    @Override
    public ResponseDto insertGroup(CreateGroupRequest createGroupRequest) {
        Course course = courseService.getCourseEntityById(createGroupRequest.getCourseId());

        Group group = groupMapper.createGroupRequestToEntity(createGroupRequest);
        group.setCourse(course);
        group.setMentor(mentorService.getMentorEntityById(createGroupRequest.getMentorId()));
        group.setEndDate(group.getStartDate().plusMonths(course.getDurationInMonth()));

        groupRepository.save(group);
        return ResponseUtil.buildSuccessResponse("Group has been successfully created.");
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
    public GroupDto updateGroupById(Long groupId, CreateGroupRequest createGroupRequest) {
        Group group = groupMapper.createGroupRequestToEntity(createGroupRequest);
        group.setId(groupId);
        return groupMapper.entityToDto(groupRepository.save(group));
    }

    @Override
    public ResponseDto archiveGroupById(Long groupId, ArchiveRequest archiveRequest) {
        Group group = getGroupEntityById(groupId);
        if (group.getStatus() == Status.ARCHIVED) {
            throw new BaseException("Group is already archived", HttpStatus.CONFLICT);
        }
        group.setReason(archiveRequest.getReason());
        group.setStatus(Status.ARCHIVED);
        groupRepository.save(group);
        return ResponseUtil.buildSuccessResponse("Group has been successfully archived.");
    }

    @Override
    public ResponseDto unarchiveGroupById(Long groupId) {
        Group group = getGroupEntityById(groupId);
        if (group.getStatus() == Status.ACTIVE) {
            throw new BaseException("Group is not archived.", HttpStatus.CONFLICT);
        }
        group.setStatus(Status.ACTIVE);
        group.setReason(null);
        groupRepository.save(group);
        return ResponseUtil.buildSuccessResponse("Group has been successfully unarchived.");
    }


    public Group getGroupEntityById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.GROUP, "id", groupId);
        });
    }
}
