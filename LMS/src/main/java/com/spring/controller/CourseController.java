package com.spring.controller;

import com.spring.model.dto.CourseDto;
import com.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public CourseDto getCourse(@PathVariable int courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @PutMapping("/{courseId}")
    public CourseDto updateCourse(@PathVariable int courseId, @RequestBody CourseDto courseDto) {
        return courseService.updateCourse(courseId, courseDto);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
    }
}