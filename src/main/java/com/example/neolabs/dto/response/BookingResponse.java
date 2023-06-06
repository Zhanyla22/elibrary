package com.example.neolabs.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {

    String dateStarted;

    String title;

    String dateEnd;

    boolean isDeadline;

    boolean isLoaned;
}
