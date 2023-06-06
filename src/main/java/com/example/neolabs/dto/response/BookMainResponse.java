package com.example.neolabs.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookMainResponse {

    Long id;

    String name;

    String author;

    String description;

    String urlImage;
}
