package com.spring.repository;

import com.spring.model.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByStudent_UserId(int studentId);
    List<Enrollment> findByCourse_CourseId(int courseId);
}
