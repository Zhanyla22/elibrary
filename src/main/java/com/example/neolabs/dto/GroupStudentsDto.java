package com.example.neolabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupStudentsDto {

    Long groupId;

    String groupName;

    List<StudentCardDto> students;

}
