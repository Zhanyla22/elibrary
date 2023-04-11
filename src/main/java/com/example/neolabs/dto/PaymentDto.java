package com.example.neolabs.dto;


import com.example.neolabs.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {

    @JsonProperty
    Long id;

    @JsonProperty
    Double amount;

    @JsonProperty
    TransactionType transactionType;

    @JsonProperty
    Long monthlyBillID;
}
