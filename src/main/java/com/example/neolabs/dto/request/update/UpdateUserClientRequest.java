package com.example.neolabs.dto.request.update;

import com.example.neolabs.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserClientRequest {

    String email;

    String phoneNumber;

    String firstName;

    String lastName;
}
