package com.example.neolabs.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavedResponse {

    Long bookId;

    Long savedId;

    String fileUrl;

    String title;

}
