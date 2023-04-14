package com.example.neolabs.service;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateGroupRequest;

import java.util.List;

public interface GroupService {

    ResponseDto insertGroup(CreateGroupRequest createGroupRequest);

    List<GroupDto> getAllGroups();

    GroupDto getGroupById(Long groupId);

    GroupDto updateGroupById(Long groupId, CreateGroupRequest createGroupRequest);

    void archiveGroupById(Long groupId, ArchiveDto groupArchiveDto);

    void blackListGroupById(Long groupId, ArchiveDto groupBlacklistDto);

}
