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
    Long totalWaitingForCall;
    Long totalCallReceived;
    Long totalAppliedForTrial;
    Long totalAttendedTrial;
    Long totalConverted;
    Double waitingForCallPercentage;
    Double callReceivedPercentage;
    Double appliedTrialPercentage;
    Double attendedTrialPercentage;
    Double conversionPercentage;
}
