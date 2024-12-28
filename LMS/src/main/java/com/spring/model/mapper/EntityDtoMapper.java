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

    @Mapping(source = "course.courseId" , target = "courseId")
    LessonDto lessonToLessonDto(Lesson lesson);

    @Mapping(target = "course.courseId" , source = "courseId")
    Lesson lessonDtoToLesson(LessonDto lessonDto);

    @Mapping(target = "courseId" , source = "course.courseId")
    @Mapping(target = "studentId" , source = "student.userId")
    EnrollmentDto enrollmentToEnrollmentDto(Enrollment enrollment);

    @Mapping(source = "courseId" , target = "course.courseId")
    @Mapping(source = "studentId" , target = "student.userId")
    Enrollment enrollmentDtoToEnrollment(EnrollmentDto enrollmentDto);

    @Mapping(target = "courseId" , source = "course.courseId")
    AssessmentDto assessmentToAssessmentDto(Assessment assessment);

    @Mapping(source = "courseId" , target = "course.courseId")
    Assessment assessmentDtoToAssessment(AssessmentDto assessmentDto);

    @Mapping(source ="assessment.assessmentId" , target ="assessmentId" )
    @Mapping(source ="student.userId" , target ="studentId" )
    GradeDto gradeToGradeDto(Grade grade);

    @Mapping(target ="assessment.assessmentId" , source ="assessmentId" )
    @Mapping(target ="student.userId" , source ="studentId" )
    Grade gradeDtoToGrade(GradeDto gradeDto);

    @Mapping(target = "courseId" , source = "course.courseId")
    @Mapping(target = "studentId" , source = "student.userId")
    AttendanceDto attendanceToAttendanceDto(Attendance attendance);

    @Mapping(source = "courseId" , target = "course.courseId")
    @Mapping(source = "studentId" , target = "student.userId")
    Attendance attendanceDtoToAttendance(AttendanceDto attendanceDto);

    @Mapping(source = "user.userId" , target = "userId")
    NotificationDto notificationToNotificationDto(Notification notification);

    @Mapping(target = "user.userId" , source = "userId")
    Notification notificationDtoToNotification(NotificationDto notificationDto);
}