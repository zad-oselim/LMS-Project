package com.spring.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendanceDto {
    private int attendanceId;
    private int courseId;
    private int studentId;
    private String attendanceDate;
    private String status;
}

