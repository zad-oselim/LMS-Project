package com.spring.controller;

import com.spring.model.dto.AssessmentDto;
import com.spring.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/{assessmentId}")
    public AssessmentDto getAssessment(@PathVariable int assessmentId) {
        return assessmentService.getAssessmentById(assessmentId);
    }

    @GetMapping("/course/{courseId}")
    public List<AssessmentDto> getAssessmentsByCourseId(@PathVariable int courseId) {
        return assessmentService.getAssessmentsByCourseId(courseId);
    }

    @PostMapping
    public AssessmentDto createAssessment(@RequestBody AssessmentDto assessmentDto) {
        return assessmentService.createAssessment(assessmentDto);
    }

    @DeleteMapping("/{assessmentId}")
    public void deleteAssessment(@PathVariable int assessmentId) {
        assessmentService.deleteAssessment(assessmentId);
    }
}
