package com.spring.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonDto {

    private int lessonId;
    private int courseId;
    private String title;
    private String content;

}
