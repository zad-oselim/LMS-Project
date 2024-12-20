package com.spring.controller;

import com.spring.model.dto.CourseDto;
import com.spring.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@Import(CourseControllerTest.MockConfiguration.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCourseById() throws Exception {
        CourseService courseService = mock(CourseService.class);
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(1);
        courseDto.setTitle("Test Course");
        when(courseService.getCourseById(1)).thenReturn(courseDto);
        mockMvc.perform(get("/api/courses/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.title").value("Test Course"));

        verify(courseService, times(1)).getCourseById(1);
    }

    @Test
    void testGetAllCourses() throws Exception {
        CourseService courseService = mock(CourseService.class);
        CourseDto course1 = new CourseDto();
        course1.setCourseId(1);
        course1.setTitle("Course One");
        CourseDto course2 = new CourseDto();
        course2.setCourseId(2);
        course2.setTitle("Course Two");
        List<CourseDto> courses = Arrays.asList(course1, course2);
        when(courseService.getAllCourses()).thenReturn(courses);
        mockMvc.perform(get("/api/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].title").value("Course One"))
                .andExpect(jsonPath("$[1].courseId").value(2))
                .andExpect(jsonPath("$[1].title").value("Course Two"));

        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void testCreateCourse() throws Exception {
        CourseService courseService = mock(CourseService.class);
        CourseDto inputCourseDto = new CourseDto();
        inputCourseDto.setTitle("New Course");
        CourseDto outputCourseDto = new CourseDto();
        outputCourseDto.setCourseId(1);
        outputCourseDto.setTitle("New Course");
        when(courseService.createCourse(any(CourseDto.class))).thenReturn(outputCourseDto);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.title").value("New Course"));

        verify(courseService, times(1)).createCourse(any(CourseDto.class));
    }

    @Test
    void testUpdateCourse() throws Exception {
        // Mocking the service
        CourseService courseService = mock(CourseService.class);
        CourseDto inputCourseDto = new CourseDto();
        inputCourseDto.setTitle("Updated Course");
        CourseDto outputCourseDto = new CourseDto();
        outputCourseDto.setCourseId(1);
        outputCourseDto.setTitle("Updated Course");
        when(courseService.updateCourse(eq(1), any(CourseDto.class))).thenReturn(outputCourseDto);
        mockMvc.perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.title").value("Updated Course"));

        verify(courseService, times(1)).updateCourse(eq(1), any(CourseDto.class));
    }

    @Test
    void testDeleteCourse() throws Exception {
        CourseService courseService = mock(CourseService.class);
        mockMvc.perform(delete("/api/courses/1"))
                .andExpect(status().isOk());

        verify(courseService, times(1)).deleteCourse(1);
    }

    @TestConfiguration
    static class MockConfiguration {
        @Bean
        public CourseService courseService() {
            return Mockito.mock(CourseService.class);
        }
    }
}