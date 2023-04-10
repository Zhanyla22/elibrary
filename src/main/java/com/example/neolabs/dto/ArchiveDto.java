package com.example.neolabs.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArchiveDto {

    //CAN BE USED FOR BLACKLIST ALSO
    String reason;

}
