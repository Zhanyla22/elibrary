package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGroupBillDto {

    @JsonProperty
    Long id;

    @JsonProperty
    Long studentId;

    @JsonProperty
    Long groupId;

    @JsonProperty
    Double studentGroupDebt;

}
