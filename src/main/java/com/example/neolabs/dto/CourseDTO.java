package com.example.neolabs.dto;


import com.example.neolabs.enums.Level;
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
public class CourseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name can't be empty or null")
    private String name;

    @NotBlank(message = "Level can't be empty or null")
    private Level level;

    @JsonProperty(value = "department", access = JsonProperty.Access.READ_ONLY)
    private DepartmentDTO department;

    @NotBlank(message = "Cost can't be empty or null")
    private Double cost;

    @NotBlank(message = "Duration can't be empty or null")
    private Integer duration_in_month;

    @NotBlank(message = "Status can't be empty or null")
    private Status status;

}
