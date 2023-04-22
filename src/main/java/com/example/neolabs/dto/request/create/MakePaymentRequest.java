package com.example.neolabs.dto.request.create;

import com.example.neolabs.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MakePaymentRequest {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long studentId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Double amount;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    TransactionType transactionType;
}
