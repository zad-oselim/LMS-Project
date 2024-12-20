package com.spring.service;

import com.spring.model.dto.GradeDto;
import com.spring.model.entity.Grade;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeService {

    private final GradeRepository gradeRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public GradeService(GradeRepository gradeRepository, EntityDtoMapper entityDtoMapper) {
        this.gradeRepository = gradeRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public GradeDto getGradeById(int gradeId) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new RuntimeException("Grade not found"));
        return entityDtoMapper.gradeToGradeDto(grade);
    }

    public List<GradeDto> getGradesByStudentId(int studentId) {
        List<Grade> grades = gradeRepository.findByStudent_UserId(studentId);
        return grades.stream().map(entityDtoMapper::gradeToGradeDto).toList();
    }

    public List<GradeDto> getGradesByAssessmentId(int assessmentId) {
        List<Grade> grades = gradeRepository.findByAssessment_AssessmentId(assessmentId);
        return grades.stream().map(entityDtoMapper::gradeToGradeDto).toList();
    }

    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = entityDtoMapper.gradeDtoToGrade(gradeDto);
        grade = gradeRepository.save(grade);
        return entityDtoMapper.gradeToGradeDto(grade);
    }

    public GradeDto updateGrade(int gradeId, GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new RuntimeException("Grade not found"));
        grade.setGrade(gradeDto.getGrade());
        grade.setFeedback(gradeDto.getFeedback());
        grade = gradeRepository.save(grade);
        return entityDtoMapper.gradeToGradeDto(grade);
    }

    public void deleteGrade(int gradeId) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new RuntimeException("Grade not found"));
        gradeRepository.delete(grade);
    }
}
