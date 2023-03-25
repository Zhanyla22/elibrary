package com.example.neolabs.dto.response;

import com.example.neolabs.dto.ApplicationDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SortedApplicationResponse {

    List<ApplicationDto> waitingForCall;

    List<ApplicationDto> callReceived;

    List<ApplicationDto> appliedForTrial;

    List<ApplicationDto> attendedTrial;

}
