package com.example.neolabs.mapper;

import com.example.neolabs.dto.BlacklistMemberDto;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.entity.Student;
import com.example.neolabs.entity.User;
import com.example.neolabs.util.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiveMapper {
    public static List<BlacklistMemberDto> entitiesToBlacklistMemberDtos(List<User> users,
                                                                         List<Mentor> mentors,
                                                                         List<Student> students){
        List<BlacklistMemberDto> members = new ArrayList<>();
        members.addAll(usersToBlacklistMemberDtos(users));
        members.addAll(mentorsToBlacklistMemberDtos(mentors));
        members.addAll(studentsToBlacklistMemberDtos(students));
        Collections.sort(members);
        return members;
    }

    private static List<BlacklistMemberDto> usersToBlacklistMemberDtos(List<User> users){
        return users.stream().map(ArchiveMapper::userToBlacklistMemberDto).collect(Collectors.toList());
    }

    private static BlacklistMemberDto userToBlacklistMemberDto(User user){
        return BlacklistMemberDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .position("Менеджер")
                .rawDate(user.getArchiveDate())
                .blacklistingDate(DateUtil.datetimeToDateFormatter.format(user.getArchiveDate()))
                .build();
    }

    private static List<BlacklistMemberDto> mentorsToBlacklistMemberDtos(List<Mentor> mentors){
        return mentors.stream().map(ArchiveMapper::mentorToBlacklistMemberDto).collect(Collectors.toList());
    }

    private static BlacklistMemberDto mentorToBlacklistMemberDto(Mentor mentor){
        return BlacklistMemberDto.builder()
                .firstName(mentor.getFirstName())
                .lastName(mentor.getLastName())
                .email(mentor.getEmail())
                .phoneNumber(mentor.getPhoneNumber())
                .position("Преподаватель")
                .rawDate(mentor.getArchiveDate())
                .blacklistingDate(DateUtil.datetimeToDateFormatter.format(mentor.getArchiveDate()))
                .build();
    }

    private static List<BlacklistMemberDto> studentsToBlacklistMemberDtos(List<Student> students){
        return students.stream().map(ArchiveMapper::studentToBlacklistMemberDto).collect(Collectors.toList());
    }

    private static BlacklistMemberDto studentToBlacklistMemberDto(Student student){
        return BlacklistMemberDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .position("Студент")
                .rawDate(student.getArchiveDate())
                .blacklistingDate(DateUtil.datetimeToDateFormatter.format(student.getArchiveDate()))
                .build();
    }
}
