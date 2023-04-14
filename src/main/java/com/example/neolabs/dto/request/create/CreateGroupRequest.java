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
public class CreateGroupRequest {

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String name;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long mentorId;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long courseId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer maxCapacity;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String startDate;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Status status;

}
