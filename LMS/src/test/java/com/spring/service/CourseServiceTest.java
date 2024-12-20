package com.spring.service;

import com.spring.model.dto.CourseDto;
import com.spring.model.entity.Course;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @InjectMocks
    private CourseService courseService;

    public CourseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourseById_CourseExists() {
        Course course = new Course();
        course.setCourseId(1);
        course.setTitle("Test Course");

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(1);
        courseDto.setTitle("Test Course");

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(entityDtoMapper.courseToCourseDto(course)).thenReturn(courseDto);

        CourseDto result = courseService.getCourseById(1);

        assertNotNull(result);
        assertEquals("Test Course", result.getTitle());
        verify(courseRepository, times(1)).findById(1);
    }
}
