package com.example.neolabs.dto.request.create;

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
public class CreateStudentGroupBillRequest {

    @JsonProperty
    Long studentId;

    @JsonProperty
    Long groupId;
}
