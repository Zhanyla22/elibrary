package com.example.neolabs.dto.request;

import com.example.neolabs.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentorCardRequestDto {

    String course;

    Status status;
}
