package com.example.neolabs.dto.request.create;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMentorRequest {

    @Email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long courseId;

}
