package com.spring.repository;

import com.spring.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Integer> {

    List<Grade> findByStudent_UserId(int studentId);
    List<Grade> findByAssessment_AssessmentId(int assessmentId);


}
