package com.example.neolabs.dto.request.create;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMentorRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long courseId;

}
