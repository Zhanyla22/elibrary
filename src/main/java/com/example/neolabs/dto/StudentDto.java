package com.example.neolabs.dto;

import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {

    Long id;

    String email;

    String firstName;

    String lastName;

    String phoneNumber;

    String gender;

    String status;

    Integer totalDebt;

    Integer totalPaymentPercentage;

    String archiveDate;

    String archiveReason;

    Boolean isArchived;

}
