package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spec_book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecBook extends BaseEntity {

    @Column(name = "inn")
    String inn;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "book_id",
            referencedColumnName = "id")
    Book book;

    @Enumerated(EnumType.STRING)
    Status status;
}
