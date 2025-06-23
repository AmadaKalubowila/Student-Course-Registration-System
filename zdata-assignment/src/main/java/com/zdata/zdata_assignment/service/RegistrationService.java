package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.model.Course;

import java.util.List;
import java.util.UUID;

public interface RegistrationService {

    void registerStudentToCourse(UUID studentId, UUID courseId);
    void dropStudentFromCourse(UUID studentId,UUID courseId);
    List<Course> getCourseByStudentId(UUID studentId);
}
