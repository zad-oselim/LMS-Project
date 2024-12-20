package com.spring.controller;

import com.spring.model.dto.AttendanceDto;
import com.spring.model.entity.Attendance;
import com.spring.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{attendanceId}")
    public AttendanceDto getAttendance(@PathVariable int attendanceId) {
        return attendanceService.getAttendanceById(attendanceId);
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceDto> getAttendanceByStudentId(@PathVariable int studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<AttendanceDto> getAttendanceByCourseId(@PathVariable int courseId) {
        return attendanceService.getAttendanceByCourseId(courseId);
    }

    @PostMapping
    public AttendanceDto createAttendance(@RequestBody AttendanceDto attendanceDto) {
        return attendanceService.createAttendance(attendanceDto);
    }

    @PutMapping("/{attendanceId}")
    public AttendanceDto updateAttendance(@PathVariable int attendanceId, @RequestBody AttendanceDto attendanceDto) {
        return attendanceService.updateAttendance(attendanceId, attendanceDto);
    }

    @DeleteMapping("/{attendanceId}")
    public void deleteAttendance(@PathVariable int attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
    }
}
