package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyBillDto {

    @JsonProperty
    Long id;

    @JsonProperty
    Long studentGroupBillId;

    @JsonProperty
    Integer monthNumber;

    @JsonProperty
    Double monthlyDept;

    @JsonProperty
    LocalDate monthlyDeadline;

}
