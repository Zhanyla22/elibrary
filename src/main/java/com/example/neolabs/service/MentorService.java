package com.example.neolabs.service;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.request.MentorCardRequestDto;


import java.util.List;

public interface MentorService {

    List<MentorCardDto> getAllMentorCard(MentorCardRequestDto mentorCardRequestDto);
}
