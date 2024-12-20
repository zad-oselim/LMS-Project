package com.spring.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDto {

    private int courseId;
    private String title;
    private String description;
    private int instructorId;
}
