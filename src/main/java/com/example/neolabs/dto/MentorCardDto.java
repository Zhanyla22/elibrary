package com.example.neolabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentorCardDto {

    Long id;

    String imageUrl;

    String firstName;

    String lastName;

    String email;

    String course;

    LocalDateTime dateArchive;

    String reasonArchive;
}
