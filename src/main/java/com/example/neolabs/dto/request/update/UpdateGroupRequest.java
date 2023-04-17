package com.example.neolabs.dto.request.create;

import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateGroupRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long mentorId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long courseId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer maxCapacity;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String startDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Status status;

}
