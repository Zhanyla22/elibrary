package com.example.neolabs.service;

import com.example.neolabs.dto.*;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.dto.request.update.UpdateMentorRequest;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MentorService {

    List<MentorCardDto> getAllMentorCards(Long courseId, Status status);

    MentorDto addNewMentor(CreateMentorRequest createMentorRequest); // FIXME: 16.04.2023 need to return some info about operation

    MentorDto saveImageMentor(Long mentorId, MultipartFile multipartFile);

    void deleteMentorById(Long id); // FIXME: 16.04.2023 need to return some info about operation

    void updateMentorById(UpdateMentorRequest updateMentorRequest, Long id); // FIXME: 16.04.2023 need to return some info about operation

    ResponseDto archiveMentorById(Long mentorId, ArchiveRequest archiveRequest, Boolean isBlacklist);

    ResponseDto unarchiveMentorById(Long mentorId);

    MentorDto getMentorById(Long id);

    List<Mentor> getBlacklist();


}
