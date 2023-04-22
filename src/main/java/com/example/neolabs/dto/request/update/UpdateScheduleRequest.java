package com.example.neolabs.dto.request.update;

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
public class UpdateScheduleRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    DayOfTheWeek dayOfTheWeek;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String startTime;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String endTime;
}
