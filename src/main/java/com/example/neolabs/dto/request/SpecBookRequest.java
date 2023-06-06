package com.example.neolabs.dto.request;

import com.example.neolabs.entity.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecBookRequest {
    Book book;
    String inn;
}
