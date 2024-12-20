package com.spring.service;

import com.spring.model.dto.AttendanceDto;
import com.spring.model.entity.Attendance;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, EntityDtoMapper entityDtoMapper) {
        this.attendanceRepository = attendanceRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public AttendanceDto getAttendanceById(int attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new RuntimeException("Attendance not found"));
        return entityDtoMapper.attendanceToAttendanceDto(attendance);
    }

    public List<AttendanceDto> getAttendanceByStudentId(int studentId) {
        List<Attendance> attendanceList = attendanceRepository.findByStudent_UserId(studentId);
        return attendanceList.stream().map(entityDtoMapper::attendanceToAttendanceDto).toList();
    }

    public List<AttendanceDto> getAttendanceByCourseId(int courseId) {
        List<Attendance> attendanceList = attendanceRepository.findByCourse_CourseId(courseId);
        return attendanceList.stream().map(entityDtoMapper::attendanceToAttendanceDto).toList();
    }

    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = entityDtoMapper.attendanceDtoToAttendance(attendanceDto);
        attendance = attendanceRepository.save(attendance);
        return entityDtoMapper.attendanceToAttendanceDto(attendance);
    }

    public AttendanceDto updateAttendance(int attendanceId, AttendanceDto attendanceDto) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendance.setStatus(Attendance.Status.valueOf(attendanceDto.getStatus()));
        attendance = attendanceRepository.save(attendance);
        return entityDtoMapper.attendanceToAttendanceDto(attendance);
    }

    public void deleteAttendance(int attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendanceRepository.delete(attendance);
    }
}