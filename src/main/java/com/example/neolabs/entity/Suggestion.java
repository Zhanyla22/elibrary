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
@Table(name = "suggestion")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suggestion extends BaseEntity {

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "published_year")
    Long publishedYear;

    @Column(name = "note")
    String note;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    User user;

    @Enumerated(EnumType.STRING)
    Status status;
}
