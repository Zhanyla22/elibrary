package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.request.AddSubjectRequest;
import com.example.neolabs.dto.response.SubjectListResponse;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject")
@Tag(name = "Предметы", description = "API Предмет ")
public class SubjectController extends BaseController {

    private final SubjectService subjectService;

    @GetMapping("/all")
    @Operation(summary = "Получение всего предмета по статусу")
    public List<SubjectListResponse> findAllByStatus(){
        return subjectService.findAllByStatus();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить новый предмет")
    public void addNewSubject(@RequestBody AddSubjectRequest addSubjectRequest){
        subjectService.addNewSubject(addSubjectRequest);
    }

}
