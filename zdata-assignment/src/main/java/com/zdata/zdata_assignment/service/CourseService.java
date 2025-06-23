package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.dto.CourseDTO;
import com.zdata.zdata_assignment.model.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course registerCourse(CourseDTO dto);
    List<Course> getAllCourses();
    Course getCourseById(UUID id);
    List<Course> getCoursesPaginated(int page, int size);
}
