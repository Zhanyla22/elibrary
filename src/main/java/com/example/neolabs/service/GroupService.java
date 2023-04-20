package com.example.neolabs.service;

import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
import com.example.neolabs.dto.request.update.UpdateGroupRequest;

import java.util.List;

public interface GroupService {

    GroupDto insertGroup(CreateGroupRequest createGroupRequest);

    List<GroupDto> getAllGroups();

    GroupDto getGroupById(Long groupId);

    GroupDto updateGroupById(Long groupId, UpdateGroupRequest updateGroupRequest);

    ResponseDto archiveGroupById(Long groupId, ArchiveRequest archiveRequest);

    ResponseDto unarchiveGroupById(Long groupId);
}
