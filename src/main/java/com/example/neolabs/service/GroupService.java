package com.example.neolabs.service;

import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;

import java.util.List;

public interface GroupService {

    ResponseDto insertGroup(GroupDto groupDto);

    List<GroupDto> getAllGroups();

    GroupDto getGroupById(Long groupId);

    GroupDto updateGroupById(Long groupId, GroupDto groupDto);

    ResponseDto archiveGroupById(Long groupId, ArchiveRequest archiveRequest);

    ResponseDto unarchiveGroupById(Long groupId);
}
