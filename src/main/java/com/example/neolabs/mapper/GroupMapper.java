package com.example.neolabs.mapper;

import com.example.neolabs.dto.GroupStudentsDto;
import com.example.neolabs.dto.GroupDto;
import com.example.neolabs.dto.request.create.CreateGroupRequest;
import com.example.neolabs.dto.request.update.UpdateGroupRequest;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.repository.GroupRepository;
import com.example.neolabs.repository.MentorRepository;
import com.example.neolabs.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupMapper {
    private final MentorRepository mentorRepository;

    public static GroupDto entityToDto(Group group){
        return GroupDto.builder()
                .id(group.getId())
                .course(CourseMapper.entityToCardDto(group.getCourse()))
                .maxCapacity(group.getMaxCapacity())
                .mentor(MentorMapper.entityToMentorCardDto(group.getMentor()))
                .startDate(group.getStartDate())
                .isArchived(group.getIsArchived())
                .archiveReason(group.getReason())
                .archiveDate(group.getArchiveDate() != null ?
                        group.getArchiveDate().format(DateUtil.datetimeToDateFormatter) : null)
                .name(group.getName())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .build();
    }

    public static Group dtoToEntity(GroupDto groupDto){
        return Group.builder()
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(groupDto.getStartDate())
                .endDate(groupDto.getEndDate())
                .status(groupDto.getStatus())
                .build();
    }

    public Group updateEntityWithUpdateRequest(Group group, UpdateGroupRequest request){
        group.setName(request.getName() != null ? request.getName() : group.getName());
        group.setMaxCapacity(request.getMaxCapacity() != null ? request.getMaxCapacity() : group.getMaxCapacity());
        group.setStartDate(request.getStartDate() != null ?
                LocalDate.parse(request.getStartDate(), DateUtil.datetimeToDateFormatter) : group.getStartDate());
        group.setStatus(request.getStatus() != null ? request.getStatus() : group.getStatus());
        group.setMentor(request.getMentorId() != null ?
                getMentorEntityById(request.getMentorId(), group.getCourse()) : group.getMentor());
        return group;
    }

    public static Group createGroupRequestToEntity(CreateGroupRequest groupDto){
        return Group.builder()
                .name(groupDto.getName())
                .maxCapacity(groupDto.getMaxCapacity())
                .startDate(LocalDate.parse(groupDto.getStartDate(), DateUtil.datetimeToDateFormatter))
                .status(groupDto.getStatus())
                .build();
    }

    public static GroupStudentsDto entityToCardDto(Group group) {
        return GroupStudentsDto.builder()
                .groupId(group.getId())
                .groupName(group.getName())
                .students(StudentMapper.entityListToCardDtoList(group.getStudents()))
                .build();
    }

    public static List<GroupDto> entityListToDtoList(List<Group> entities){
        return entities.stream().map(GroupMapper::entityToDto).collect(Collectors.toList());
    }

    private Mentor getMentorEntityById(Long mentorId, Course course) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(
                        () -> new EntityNotFoundException(EntityEnum.MENTOR, "id", mentorId)
                );
        if (mentor.getCourse().getId() != course.getId()) {
            throw new BaseException("Mentor does not belong to the course of group.", HttpStatus.CONFLICT);
        }
        return mentor;
    }
}
