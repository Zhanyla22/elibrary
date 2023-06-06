package com.example.neolabs.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suggest {
//TODO: FINISH
    String title;

    String author;

    Long year;

    String note;
}
