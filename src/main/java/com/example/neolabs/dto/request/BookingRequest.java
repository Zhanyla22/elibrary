package com.example.neolabs.dto.request;

import com.example.neolabs.entity.SpecBook;
import com.example.neolabs.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {

    String inn;

    String bookName;

    User user;

    LocalDateTime getDate;

    LocalDateTime returnDate;
}
