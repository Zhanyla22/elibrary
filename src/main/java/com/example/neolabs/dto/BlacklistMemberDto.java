package com.example.neolabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlacklistMemberDto implements Comparable<BlacklistMemberDto>{

    String email;
    String firstName;
    String lastName;
    String phoneNumber;
    String position;
    String blacklistingDate;
    String reason;

    @JsonIgnore
    LocalDateTime rawDate;

    @Override
    public int compareTo(BlacklistMemberDto o) {
        return getRawDate().compareTo(o.getRawDate());
    }
}
