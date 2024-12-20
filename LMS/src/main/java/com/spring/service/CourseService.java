package com.spring.service;

import com.spring.model.dto.CourseDto;
import com.spring.model.entity.Course;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, EntityDtoMapper entityDtoMapper) {
        this.courseRepository = courseRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public CourseDto getCourseById(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return entityDtoMapper.courseToCourseDto(course);
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(entityDtoMapper::courseToCourseDto).toList();
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = entityDtoMapper.courseDtoToCourse(courseDto);
        course = courseRepository.save(course);
        return entityDtoMapper.courseToCourseDto(course);
    }

    public CourseDto updateCourse(int courseId, CourseDto courseDto) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course = courseRepository.save(course);
        return entityDtoMapper.courseToCourseDto(course);
    }

    public void deleteCourse(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }
}