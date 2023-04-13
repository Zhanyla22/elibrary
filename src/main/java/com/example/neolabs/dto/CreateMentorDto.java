package com.example.neolabs.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMentorDto {

    String email;

    String firstName;

    String lastName;

    String phoneNumber;

    String courseName;
}
