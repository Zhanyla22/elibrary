package com.example.neolabs.dto;

import com.example.neolabs.entity.Group;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {
    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("phone_number")
    String phoneNumber;

    Gender gender;

    Status status;

    @JsonProperty("total_debt")
    Integer totalDebt;

    @JsonProperty("payment_percentage")
    Integer totalPaymentPercentage;

    Set<Group> groups;
}
