package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationAnalyticsResponse {
    Integer totalApplications;
    Integer totalCallReceived;
    Integer totalAppliedForTrial;
    Integer totalAttendedTrial;
    Integer totalConversion;
}
