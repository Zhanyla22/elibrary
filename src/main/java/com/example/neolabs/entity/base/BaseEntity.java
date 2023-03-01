package com.example.neolabs.entity.base;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date_created")
    LocalDateTime dateCreated;

    @Column(name = "date_updated")
    LocalDateTime dateUpdated;

    @PrePersist
    public void prePersist(){
        this.dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.dateUpdated = LocalDateTime.now();
    }

}
