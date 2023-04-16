package com.example.neolabs.service;

import com.example.neolabs.dto.BlacklistMemberDto;

import java.util.List;

public interface ArchiveService {
    List<BlacklistMemberDto> getBlacklist();
}
