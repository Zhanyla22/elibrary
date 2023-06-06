package com.example.neolabs.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingInfoResponse {

    Long bookingId;

    Long bookId;

    String title;
    
    String firstName;
    
    String lastName;
    
    String group;
}
