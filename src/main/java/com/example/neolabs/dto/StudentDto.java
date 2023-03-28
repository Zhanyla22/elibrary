package com.example.neolabs.dto;

import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {

    @NotEmpty
    @Email
    @JsonProperty("email")
    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("phone_number")
    String phoneNumber;

    @JsonProperty("gender")
    Gender gender;

    @JsonProperty("status")
    Status status;

    @JsonProperty("total_debt")
    Integer totalDebt;

    @JsonProperty("payment_percentage")
    Integer totalPaymentPercentage;

    @JsonProperty("groups")
    List<GroupDto> groups;
}
