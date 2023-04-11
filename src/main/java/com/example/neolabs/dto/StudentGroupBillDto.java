package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
