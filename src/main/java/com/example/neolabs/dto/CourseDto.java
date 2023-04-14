package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer cost;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Boolean isArchived;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer durationInMonth;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer numberOfLessons;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String imageUrl;
}
