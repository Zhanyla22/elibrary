package com.example.neolabs.service.impl;

import com.example.neolabs.dto.CourseCardDto;
import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.dto.request.update.UpdateCourseRequest;
import com.example.neolabs.entity.Course;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.CourseMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.service.CourseService;
import com.example.neolabs.service.ImageUploadService;
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ImageUploadServiceImpl imageUploadService;
    private final OperationServiceImpl operationService;

    @Override
    public List<CourseCardDto> getAllCourses() {
        return CourseMapper.entityListToCardDtoList(courseRepository.findAll());
    }

    @Override
    public CourseDto insertCourse(CreateCourseRequest createCourseRequest) {
        if (courseRepository.existsByName(createCourseRequest.getName())) {
            throw new BaseException("Course name is already in use.", HttpStatus.CONFLICT);
        }
        Course course = CourseMapper.createRequestToEntity(createCourseRequest);
        course.setStatus(Status.ACTIVE);
        course.setIsArchived(false);
        course = courseRepository.save(course);
        operationService.recordCourseOperation(course, OperationType.CREATE);
        return CourseMapper.entityToDto(course);
    }

    @Override
    public CourseCardDto updateCourseById(Long courseId, UpdateCourseRequest updateCourseRequest) {
        Course course = getCourseEntityById(courseId);
        course = CourseMapper.updateEntity(course, updateCourseRequest);
        operationService.recordCourseOperation(course, OperationType.UPDATE);
        return CourseMapper.entityToCardDto(courseRepository.save(course));
    }

    @Override
    public ResponseDto archiveCourseById(Long courseId, ArchiveRequest archiveRequest) {
        Course course = getCourseEntityById(courseId);
        if (course.getStatus() == Status.ARCHIVED) {
            throw new BaseException("course is already archived", HttpStatus.CONFLICT);
        }
        course.setReason(archiveRequest.getReason());
        course.setStatus(Status.ARCHIVED);
        course.setArchiveDate(LocalDateTime.now(DateUtil.getZoneId()));
        course.setIsArchived(true);
        operationService.recordCourseOperation(courseRepository.save(course), OperationType.ARCHIVE);
        return ResponseUtil.buildSuccessResponse("Course has been successfully archived.");
    }

    @Override
    public ResponseDto unarchiveCourseById(Long courseId) {
        Course course = getCourseEntityById(courseId);
        if (course.getStatus() == Status.ACTIVE) {
            throw new BaseException("Course is already active.", HttpStatus.CONFLICT);
        }
        course.setStatus(Status.ACTIVE);
        course.setReason(null);
        course.setArchiveDate(null);
        operationService.recordCourseOperation(courseRepository.save(course), OperationType.UNARCHIVE);
        return ResponseUtil.buildSuccessResponse("Course has been successfully unarchived.");
    }

    @Override
    public CourseCardDto saveImageCourse(Long courseId, MultipartFile multipartFile) {
        Course course = getCourseEntityById(courseId);
        course.setImageUrl(imageUploadService.saveImage(multipartFile));
        return CourseMapper.entityToCardDto(courseRepository.save(course));
    }

    @Override
    public CourseDto deleteCourseById(Long id) {
        Course course = getCourseEntityById(id);
//        courseRepository.delete(course);
        return CourseMapper.entityToDto(course);
    }

    @Override
    public Course getCourseEntityById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.COURSE, "id", id);
        });
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return CourseMapper.entityToDto(getCourseEntityById(courseId));
    }

}
