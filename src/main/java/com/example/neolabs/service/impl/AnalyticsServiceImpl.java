package com.example.neolabs.service.impl;

import com.example.neolabs.dto.response.analytics.*;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.enums.Role;
import com.example.neolabs.mapper.AnalyticsMapper;
import com.example.neolabs.repository.*;
import com.example.neolabs.repository.analytics.ApplicationStatusHistoryRepository;
import com.example.neolabs.repository.operation.CourseOperationRepository;
import com.example.neolabs.service.AnalyticsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnalyticsServiceImpl implements AnalyticsService {

    final StudentRepository studentRepository;
    final ApplicationStatusHistoryRepository appStatusRepository;
    final ApplicationRepository applicationRepository;
    final MentorRepository mentorRepository;
    final GroupRepository groupRepository;
    final CourseRepository courseRepository;
    final CourseOperationRepository courseOperationRepository;
    final UserRepository userRepository;

    @Override
    public GeneralAnalyticsResponse getGeneralAnalytics() {
        return GeneralAnalyticsResponse.builder()
                .totalApplications(applicationRepository.count())
                .totalCourses(courseRepository.count())
                .totalGroups(groupRepository.count())
                .totalManagers(userRepository.countByRole(Role.ROLE_MANAGER))
                .totalMentors(mentorRepository.count())
                .totalStudents(studentRepository.count())
                .build();
    }

    @Override
    public GenderAnalyticsResponse getGenderAnalytics() {
        long maleApplicants = applicationRepository.countByGender(Gender.MALE);
        long femaleApplicants = applicationRepository.countByGender(Gender.FEMALE);
        long allApplicants = maleApplicants + femaleApplicants;
        long maleStudents = studentRepository.countByGender(Gender.MALE);
        long femaleStudents = studentRepository.countByGender(Gender.FEMALE);
        long allStudents = maleStudents + femaleStudents;
        double applicationMalePercentage = (maleApplicants * 1000 / allApplicants) / 10.0;
        double applicationFemalePercentage = 100.0 - applicationMalePercentage;
        double studentMalePercentage = (maleStudents * 1000 / allStudents) / 10.0;
        double studentFemalePercentage = 100.0 - studentMalePercentage;
        double studentMaleConversionPercentage = (maleStudents * 1000 / maleApplicants) / 10.0;
        double studentFemaleConversionPercentage = (femaleStudents * 1000 / femaleApplicants) / 10.0;
        return GenderAnalyticsResponse.builder()
                .applicationMaleNumber(maleApplicants)
                .applicationFemaleNumber(femaleApplicants)
                .applicationMalePercentage(applicationMalePercentage)
                .applicationFemalePercentage(applicationFemalePercentage)
                .studentMaleNumber(maleStudents)
                .studentFemaleNumber(femaleStudents)
                .studentMalePercentage(studentMalePercentage)
                .studentFemalePercentage(studentFemalePercentage)
                .studentMaleConversionPercentage(studentMaleConversionPercentage)
                .studentFemaleConversionPercentage(studentFemaleConversionPercentage)
                .build();
    }

    @Override
    public CourseAnalyticsResponse getCourseAnalytics() {
        long totalCourses = courseRepository.count();
        long totalEnrollments = courseOperationRepository.countByOperationType(OperationType.ENROLLMENT);
        List<CourseAnalyticsDto> courses = AnalyticsMapper.courseEnrollmentListToDtoList(
                courseOperationRepository.getTotalCoursesEnrollments(),
                totalEnrollments);
        return CourseAnalyticsResponse.builder()
                .totalCourses(totalCourses)
                .totalEnrollments(totalEnrollments)
                .courses(courses)
                .build();
    }

    @Override
    public ApplicationAnalyticsResponse getApplicationAnalytics() {
        long totalWaitingForCall = appStatusRepository.countByStatusBeforeArchiving(ApplicationStatus.WAITING_FOR_CALL);
        long totalCallReceived = appStatusRepository.countByStatusBeforeArchiving(ApplicationStatus.CALL_RECEIVED);
        long totalAppliedForTrial = appStatusRepository.countByStatusBeforeArchiving(ApplicationStatus.APPLIED_FOR_TRIAL);
        long totalAttendedTrial = appStatusRepository.countByStatusBeforeArchiving(ApplicationStatus.ATTENDED_TRIAL);
        long totalConverted = appStatusRepository.countByIsConverted(true);
        totalWaitingForCall = totalWaitingForCall + totalCallReceived + totalAppliedForTrial + totalAttendedTrial;
        totalCallReceived = totalCallReceived + totalAppliedForTrial + totalAttendedTrial;
        totalAppliedForTrial = totalAppliedForTrial + totalAttendedTrial;
        return ApplicationAnalyticsResponse.builder()
                .totalWaitingForCall(totalWaitingForCall)
                .totalCallReceived(totalCallReceived)
                .totalAppliedForTrial(totalAppliedForTrial)
                .totalAttendedTrial(totalAttendedTrial)
                .totalConverted(totalConverted)
                .callReceivedPercentage((totalCallReceived * 1000 / totalWaitingForCall) / 10.0)
                .waitingForCallPercentage(100.0)
                .appliedTrialPercentage((totalAppliedForTrial * 1000 / totalWaitingForCall) / 10.0)
                .attendedTrialPercentage((totalAttendedTrial * 1000 / totalWaitingForCall) / 10.0)
                .conversionPercentage((totalConverted * 1000 / totalWaitingForCall) / 10.0)
                .build();
    }

    @Override
    public BigAnalyticsResponse getAllAnalytics() {
        return BigAnalyticsResponse.builder()
                .general(getGeneralAnalytics())
                .application(getApplicationAnalytics())
                .course(getCourseAnalytics())
                .gender(getGenderAnalytics())
                .build();
    }
}
