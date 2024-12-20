package com.spring.repository;

import com.spring.model.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    List<Assessment> findByCourse_CourseId(int courseId);
}
