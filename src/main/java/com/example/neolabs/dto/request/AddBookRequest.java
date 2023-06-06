package com.example.neolabs.dto.request;

import com.example.neolabs.enums.BookType;
import com.example.neolabs.enums.SubjectType;
import com.example.neolabs.enums.Buyer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequest {

    @NotNull
    String title;

    @NotNull
    String author;

    @NotNull
    String description;

    @NotNull
    Long quantity;

    @NotNull
    SubjectType subjectType;

    @NotNull
    Long subject;

    @NotNull
    String buyer;

    Double cost;

    LocalDate publishedYear;

    LocalDate yearBought;

    @NotNull
    BookType bookType;
}
