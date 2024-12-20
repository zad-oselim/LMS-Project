package com.spring.controller;

import com.spring.model.dto.EnrollmentDto;
import com.spring.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{enrollmentId}")
    public EnrollmentDto getEnrollment(@PathVariable int enrollmentId) {
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentDto> getEnrollmentsByCourseId(@PathVariable int courseId) {
        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentDto> getEnrollmentsByStudentId(@PathVariable int studentId) {
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    @PostMapping
    public EnrollmentDto createEnrollment(@RequestBody EnrollmentDto enrollmentDto) {
        return enrollmentService.createEnrollment(enrollmentDto);
    }

    @DeleteMapping("/{enrollmentId}")
    public void deleteEnrollment(@PathVariable int enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
    }
}
