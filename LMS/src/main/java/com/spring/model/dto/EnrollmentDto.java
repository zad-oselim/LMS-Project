package com.spring.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnrollmentDto {

    private int enrollmentId;
    private int courseId;
    private int studentId;
}