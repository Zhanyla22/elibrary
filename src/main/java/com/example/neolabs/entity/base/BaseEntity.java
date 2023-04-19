package com.example.neolabs.entity.base;

import com.example.neolabs.util.DateUtil;
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

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "updated_date")
    LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    LocalDateTime deletedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now(DateUtil.getZoneId());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now(DateUtil.getZoneId());
    }
}
