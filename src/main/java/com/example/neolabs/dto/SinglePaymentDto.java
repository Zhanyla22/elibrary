package com.example.neolabs.dto;


import com.example.neolabs.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinglePayment {

    @JsonProperty
    Double value;

    @JsonProperty
    TransactionType transactionType;
}
