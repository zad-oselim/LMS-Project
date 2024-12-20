package com.spring.repository;

import com.spring.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByStudent_UserId(int studentId);
    List<Attendance> findByCourse_CourseId(int courseId);
}
