package com.example.neolabs.dto.request.create;

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
public class UpdateCourseRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer cost;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer durationInMonth;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer numberOfLessons;

}
