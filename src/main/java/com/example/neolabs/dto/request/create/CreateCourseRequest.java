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
public class CreateCourseRequest {

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String name;

    @NotNull(message = "Cost can't be empty or null")
    @JsonProperty(value = "cost")
    Integer cost;

    @NotNull(message = "Duration can't be empty or null")
    @JsonProperty(value = "durationInMonth")
    Integer durationInMonth;

    @NotNull
    @JsonProperty(value = "numberOfLessons")
    Integer numberOfLessons;

}
