package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.BookingStatus;
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
@Table(name = "booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(columnDefinition = "spec_book_id",
            referencedColumnName = "id")
    SpecBook specBook;

    @ManyToOne
    @JoinColumn(columnDefinition = "book_id",
            referencedColumnName = "id")
    Book book;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    User user;

    @Column(name = "get_date")
    LocalDate getDate;

    @Column(name = "return_date")
    LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;
}
