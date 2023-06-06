package com.example.neolabs.dto.request;

import com.example.neolabs.enums.BookType;
import com.example.neolabs.enums.SubjectType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBookRequest {

    String title;

    String author;

    String description;

    Long quantity;

    SubjectType subjectType;
    
    Long subject;

    String buyer;

    Double cost;

    LocalDate publishedYear;

    LocalDate yearBought;

    BookType bookType;
}
