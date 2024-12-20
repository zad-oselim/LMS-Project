package com.spring.controller;

import com.spring.model.dto.GradeDto;
import com.spring.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/{gradeId}")
    public GradeDto getGrade(@PathVariable int gradeId) {
        return gradeService.getGradeById(gradeId);
    }

    @GetMapping("/student/{studentId}")
    public List<GradeDto> getGradesByStudent(@PathVariable int studentId) {
        return gradeService.getGradesByStudentId(studentId);
    }

    @PostMapping
    public GradeDto createGrade(@RequestBody GradeDto gradeDto) {
        return gradeService.createGrade(gradeDto);
    }

    @PutMapping("/{gradeId}")
    public GradeDto updateGrade(@PathVariable int gradeId, @RequestBody GradeDto gradeDto) {
        return gradeService.updateGrade(gradeId, gradeDto);
    }

    @DeleteMapping("/{gradeId}")
    public void deleteGrade(@PathVariable int gradeId) {
        gradeService.deleteGrade(gradeId);
    }
}
