package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.BookType;
import com.example.neolabs.enums.SubjectType;
import com.example.neolabs.enums.Buyer;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {
    @Column(name = "title", length = 2000)
    String title;
    @Column(name = "author", length = 2000)
    String author;
    @Column(name = "url_image", length = 2000)
    String urlImage;
    @Column(name = "description", length = 5000)
    String description;
    @Column(name = "published_year")
    LocalDate publishedYear;
    @Column(name = "year_bought")
    LocalDate yearBought;
    @Column(name = "cost")
    Double cost;
    @Column
    String buyer;
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "subject_id",
            referencedColumnName = "id")
    Subject subject;
    @Enumerated(EnumType.STRING)
    Status status;
    //ebook, phusbook
    @Enumerated(EnumType.STRING)
    BookType bookType;
    //osnovnoi, dop
    @Enumerated(EnumType.STRING)
    SubjectType subjectType;
    @Column(name ="pdf_link")
    String pdfLink;
    @Column(name = "quantity")
    Long quantity;
}
