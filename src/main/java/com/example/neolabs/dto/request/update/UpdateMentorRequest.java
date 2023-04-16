package com.example.neolabs.dto.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMentorRequest {

    String email;

    String firstName;

    String lastName;

    String phoneNumber;

    String patentNumber;

    Long courseId;

    Double salary;
}
