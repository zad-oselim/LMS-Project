package com.spring.service;

import com.spring.model.dto.LessonDto;
import com.spring.model.entity.Lesson;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public LessonService(LessonRepository lessonRepository, EntityDtoMapper entityDtoMapper) {
        this.lessonRepository = lessonRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public LessonDto getLessonById(int lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
        return entityDtoMapper.lessonToLessonDto(lesson);
    }

    public List<LessonDto> getLessonsByCourseId(int courseId) {
        List<Lesson> lessons = lessonRepository.findByCourse_CourseId(courseId);
        return lessons.stream().map(entityDtoMapper::lessonToLessonDto).toList();
    }

    public LessonDto createLesson(LessonDto lessonDto) {
        Lesson lesson = entityDtoMapper.lessonDtoToLesson(lessonDto);
        lesson = lessonRepository.save(lesson);
        return entityDtoMapper.lessonToLessonDto(lesson);
    }

    public LessonDto updateLesson(int lessonId, LessonDto lessonDto) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        lesson = lessonRepository.save(lesson);
        return entityDtoMapper.lessonToLessonDto(lesson);
    }

    public void deleteLesson(int lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
        lessonRepository.delete(lesson);
    }
}