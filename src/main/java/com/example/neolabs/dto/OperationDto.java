package com.example.neolabs.dto;

import com.example.neolabs.dto.UserDto;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OperationDto implements Comparable<OperationDto> {
    @JsonProperty("type")
    OperationType type;

    @JsonProperty("targetType")
    String targetType;

    @JsonProperty("targetId")
    Long targetId;

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("firstName")
    String lastName;

    @JsonProperty("description")
    String description;

    @JsonProperty("date")
    String date;

    @JsonProperty("time")
    String time;

    @JsonIgnore
    LocalDateTime rawDate;

    @Override
    public int compareTo(OperationDto operation) {
        return getRawDate().compareTo(operation.getRawDate());
    }
}
