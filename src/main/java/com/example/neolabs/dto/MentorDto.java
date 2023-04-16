package com.example.neolabs.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentorDto {

    String firstName;

    String lastName;

    String patentNumber;

    String phoneNumber;

    String email;

    String courseName;

    List<String> groupName;

    List<ScheduleDto> scheduleDto;

}
