package com.example.neolabs.service.impl;

import com.example.neolabs.dto.BlacklistMemberDto;
import com.example.neolabs.mapper.ArchiveMapper;
import com.example.neolabs.service.ArchiveService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArchiveServiceImpl implements ArchiveService {

    final UserServiceImpl userService;
    final MentorServiceImpl mentorService;
    final StudentServiceImpl studentService;

    @Override
    public List<BlacklistMemberDto> getBlacklist() {
        return ArchiveMapper.entitiesToBlacklistMemberDtos(userService.getBlacklist(), mentorService.getBlacklist(), studentService.getBlacklist());
    }
}
