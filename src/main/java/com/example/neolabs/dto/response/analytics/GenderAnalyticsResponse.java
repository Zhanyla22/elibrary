package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenderAnalyticsResponse {
    Double studentMalePercentage;
    Double studentFemalePercentage;
    Double applicationMalePercentage;
    Double applicationFemalePercentage;
    Double studentMaleConversionPercentage;
    Double studentFemaleConversionPercentage;

    Integer studentMaleNumber;
    Integer studentFemaleNumber;
    Integer applicationMaleNumber;
    Integer applicationFemaleNumber;
}
