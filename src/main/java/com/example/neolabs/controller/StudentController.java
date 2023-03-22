package com.example.neolabs.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/v1/students")
@Tag(name = "Student Resource", description = "The Student API ")
public class StudentController {
}
