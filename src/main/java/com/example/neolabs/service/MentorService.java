package com.example.neolabs.service;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.request.MentorCardRequestDto;
import com.example.neolabs.enums.Status;


import java.util.List;

public interface MentorService {

    List<MentorCardDto> getAllMentorCard(String department, Status status);
}
