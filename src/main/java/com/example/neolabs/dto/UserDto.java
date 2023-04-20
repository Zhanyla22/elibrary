package com.example.neolabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotEmpty
    @Email
    String email;

    String phoneNumber;

    String firstName;

    String lastName;

    String lastVisitDate;

    String lastVisitTime;

    String archiveDate;

    String archiveReason;

    Boolean isArchived;
}
