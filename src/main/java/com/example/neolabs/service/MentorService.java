package com.example.neolabs.service;

import com.example.neolabs.dto.CreateMentorDto;
import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.UpdateMentorDto;
import com.example.neolabs.dto.request.MentorCardRequestDto;
import com.example.neolabs.enums.Status;


import java.util.List;

public interface MentorService {

    List<MentorCardDto> getAllMentorCard(Long departmentId, Status status);

    void addNewMentor(CreateMentorDto createMentorDto);

    void deleteMentorById(Long id);

    void updateMentorById(UpdateMentorDto updateMentorDto, Long id);
}
