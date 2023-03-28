package com.example.neolabs.dto;

import com.example.neolabs.enums.EntityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDto {

    @JsonProperty(value = "operation_target")
    EntityEnum operationTarget;

    @JsonProperty(value = "operation_target_id")
    Long operationTargetId;

    @JsonProperty(value = "user")
    UserDto user;

    @JsonProperty(value = "description")
    String description;

    @JsonProperty(value = "time")
    LocalDateTime time;
}
