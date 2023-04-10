package com.example.neolabs.dto;


import com.example.neolabs.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {

    @JsonProperty
    Double amount;

    @JsonProperty
    TransactionType transactionType;

    @JsonProperty
    Long monthlyBillID;

}
