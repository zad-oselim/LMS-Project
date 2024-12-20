package com.spring.model.mapper;

import com.spring.model.dto.*;
import com.spring.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @Mapping(target = "instructorId" , source = "instructor.userId")
    CourseDto courseToCourseDto(Course course);

    @Mapping(source = "instructorId" , target = "instructor.userId")
    Course courseDtoToCourse(CourseDto courseDto);

    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);

    @Mapping(target = "courseId" , source = "course.courseId")
    @Mapping(target = "studentId" , source = "student.userId")
    EnrollmentDto enrollmentToEnrollmentDto(Enrollment enrollment);

    @Mapping(source = "courseId" , target = "course.courseId")
    @Mapping(source = "studentId" , target = "student.userId")
    Enrollment enrollmentDtoToEnrollment(EnrollmentDto enrollmentDto);

    AssessmentDto assessmentToAssessmentDto(Assessment assessment);

    Assessment assessmentDtoToAssessment(AssessmentDto assessmentDto);

    GradeDto gradeToGradeDto(Grade grade);

    Grade gradeDtoToGrade(GradeDto gradeDto);

    AttendanceDto attendanceToAttendanceDto(Attendance attendance);

    Attendance attendanceDtoToAttendance(AttendanceDto attendanceDto);

    NotificationDto notificationToNotificationDto(Notification notification);

    Notification notificationDtoToNotification(NotificationDto notificationDto);
}