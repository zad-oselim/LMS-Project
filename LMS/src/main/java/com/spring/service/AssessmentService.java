package com.spring.service;

import com.spring.model.dto.AssessmentDto;
import com.spring.model.entity.Assessment;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, EntityDtoMapper entityDtoMapper) {
        this.assessmentRepository = assessmentRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public AssessmentDto getAssessmentById(int assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new RuntimeException("Assessment not found"));
        return entityDtoMapper.assessmentToAssessmentDto(assessment);
    }

    public List<AssessmentDto> getAssessmentsByCourseId(int courseId) {
        List<Assessment> assessments = assessmentRepository.findByCourse_CourseId(courseId);
        return assessments.stream().map(entityDtoMapper::assessmentToAssessmentDto).toList();
    }

    public AssessmentDto createAssessment(AssessmentDto assessmentDto) {
        Assessment assessment = entityDtoMapper.assessmentDtoToAssessment(assessmentDto);
        assessment = assessmentRepository.save(assessment);
        return entityDtoMapper.assessmentToAssessmentDto(assessment);
    }

    public void deleteAssessment(int assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new RuntimeException("Assessment not found"));
        assessmentRepository.delete(assessment);
    }
}
