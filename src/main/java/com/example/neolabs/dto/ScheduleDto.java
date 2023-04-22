package com.example.neolabs.dto;

import com.example.neolabs.enums.DayOfTheWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long groupId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    DayOfTheWeek dayOfTheWeek;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String startTime;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String endTime;
}
