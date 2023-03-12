package com.example.neolabs.dto;

import com.example.neolabs.enums.EntityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    EntityEnum operationTarget;

    Long operationTargetId;

    UserDto user;

    String description;

    LocalDateTime time;

}
