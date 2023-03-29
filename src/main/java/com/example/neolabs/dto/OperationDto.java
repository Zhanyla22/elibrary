package com.example.neolabs.dto;

import com.example.neolabs.dto.UserDto;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
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
    @JsonProperty("type")
    OperationType type;

    @JsonProperty("target_type")
    EntityEnum targetType;

    @JsonProperty("target")
    Object target;

    @JsonProperty("target_id")
    Long targetId;

    @JsonProperty("user")
    UserDto user;

    @JsonProperty("description")
    String description;

    @JsonProperty("date")
    String date;

    @JsonProperty("time")
    String time;
}
