package com.example.neolabs.service.impl;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.request.MentorCardRequestDto;
import com.example.neolabs.entity.Department;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import com.example.neolabs.mapper.MentorMapper;
import com.example.neolabs.repository.MentorRepository;
import com.example.neolabs.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    final MentorRepository mentorRepository;

    @Override
    public List<MentorCardDto> getAllMentorCard(String department, Status status) {
        List<Mentor> mentors = mentorRepository.findAllByDepartmentNameAndStatus(department,status);
        List<MentorCardDto> mentorCardDtos = new ArrayList<>();
        for (Mentor m: mentors){
            mentorCardDtos.add(MentorMapper.mentorEntityToMentorCardDto(m));
        }
        return mentorCardDtos;
    }
}
