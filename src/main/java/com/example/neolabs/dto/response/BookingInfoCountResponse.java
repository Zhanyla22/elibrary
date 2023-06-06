package com.example.neolabs.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingInfoCountResponse {

    List<BookingInfoResponse> bookingInfoResponse;

    int count;
}
