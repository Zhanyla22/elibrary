package com.example.neolabs.dto.request;

import com.example.neolabs.enums.Group;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {

    String name;

    Group group;
}
