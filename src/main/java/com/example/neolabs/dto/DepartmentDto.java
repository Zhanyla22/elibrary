package com.example.neolabs.dto;

import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name can't be empty or null")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "Status can't be empty or null")
    @JsonProperty(value = "status")
    private Status status;
}