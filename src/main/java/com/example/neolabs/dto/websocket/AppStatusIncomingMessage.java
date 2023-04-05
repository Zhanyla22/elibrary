package com.example.neolabs.dto.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppStatusIncomingMessage {
    @JsonProperty("applicationId")
    Long applicationId;

    @JsonProperty("newStatus")
    Integer newStatus;

    @JsonProperty("oldStatus")
    Integer oldStatus;
}
