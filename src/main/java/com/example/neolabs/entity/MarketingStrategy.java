package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marketing_strategies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarketingStrategy extends BaseEntity {

    @Column(name = "name")
    String name;
}
