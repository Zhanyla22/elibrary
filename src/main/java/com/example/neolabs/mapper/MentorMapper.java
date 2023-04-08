package com.example.neolabs.mapper;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.entity.Mentor;

public class MentorMapper {
    public static MentorCardDto mentorEntityToMentorCardDto(Mentor mentor){
        MentorCardDto mentorCardDto = new MentorCardDto();
        mentorCardDto.setEmail(mentor.getEmail());
        mentorCardDto.setFirstName(mentor.getFirstName());
        mentorCardDto.setLastName(mentor.getLastName());
        mentorCardDto.setImageUrl(mentor.getImageUrl());
        mentorCardDto.setDepartment(mentor.getDepartment().getName());
        mentorCardDto.setDateArchive(mentor.getDateArchive());
        mentorCardDto.setReasonArchive(mentor.getReasonArchive());
        return mentorCardDto;
    }
}
