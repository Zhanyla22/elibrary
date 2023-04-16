package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralAnalyticsResponse {
    Integer totalStudentsAdded;
    Integer totalApplicationsAdded;
    Integer totalStudentsArchived;
    Integer totalApplicationsArchived;
    Integer totalStudentsLeft;
    Integer totalStudentsFinished;
    Integer totalStudentsBlacklisted;

    // FIXME: 16.04.2023 dreams
//    Integer totalManagerLogins;
//    Integer totalNewManagers;
//    Integer totalGroupsFinished;
}
