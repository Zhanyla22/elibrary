package com.example.neolabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMentorDto {

    String email;

    String firstName;

    String lastName;

    String phoneNumber;

    String patentNumber;

    String departmentName;

    Double salary;
}
