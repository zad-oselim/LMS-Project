package com.spring.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeDto {

    private int gradeId;
    private int assessmentId;
    private int studentId;
    private Float grade;
    private String feedback;
}
