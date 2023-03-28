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
@Table(name = "mentors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentor extends BaseEntity {

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "patent_number")
    String patentNumber;

    @Column(name = "salary")
    Double salary;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "department_id",
            referencedColumnName = "id")
    Department department;

}


