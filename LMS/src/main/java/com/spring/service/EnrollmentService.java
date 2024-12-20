package com.spring.service;

import com.spring.model.dto.EnrollmentDto;
import com.spring.model.entity.Enrollment;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, EntityDtoMapper entityDtoMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public EnrollmentDto getEnrollmentById(int enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return entityDtoMapper.enrollmentToEnrollmentDto(enrollment);
    }

    public List<EnrollmentDto> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourse_CourseId(courseId);
        return enrollments.stream().map(entityDtoMapper::enrollmentToEnrollmentDto).toList();
    }

    public List<EnrollmentDto> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudent_UserId(studentId);
        return enrollments.stream().map(entityDtoMapper::enrollmentToEnrollmentDto).toList();
    }

    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = entityDtoMapper.enrollmentDtoToEnrollment(enrollmentDto);
        enrollment = enrollmentRepository.save(enrollment);
        return entityDtoMapper.enrollmentToEnrollmentDto(enrollment);
    }

    public void deleteEnrollment(int enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }
}
