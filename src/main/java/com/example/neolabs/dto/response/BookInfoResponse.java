package com.example.neolabs.dto.response;

import com.example.neolabs.enums.BookType;
import com.example.neolabs.enums.Buyer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookInfoResponse {

    Long id;

    String title;

    String author;

    String publishedYear;

    String boughtYear;

    String buyer;

    String description;

    String urlImage;

    String subject;

    String pdfLink;

    Long bookInLibrary;

    boolean isAvailable;

    BookType bookType;

    boolean isSaved;
}
