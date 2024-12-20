package com.spring.controller;

import com.spring.model.dto.LessonDto;
import com.spring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{lessonId}")
    public LessonDto getLesson(@PathVariable int lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping("/course/{courseId}")
    public List<LessonDto> getLessonsByCourseId(@PathVariable int courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }

    @PostMapping
    public LessonDto createLesson(@RequestBody LessonDto lessonDto) {
        return lessonService.createLesson(lessonDto);
    }

    @PutMapping("/{lessonId}")
    public LessonDto updateLesson(@PathVariable int lessonId, @RequestBody LessonDto lessonDto) {
        return lessonService.updateLesson(lessonId, lessonDto);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable int lessonId) {
        lessonService.deleteLesson(lessonId);
    }
}
