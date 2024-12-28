package com.spring.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AttendanceDto {
    private int attendanceId;
    private int courseId;
    private int studentId;
    private Date attendanceDate;
    private String status;
}

