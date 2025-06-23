package com.zdata.zdata_assignment.serviceImpl;

import com.zdata.zdata_assignment.dto.CourseDTO;
import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CourseServiceImpl implements CourseService {
    private final List<Course> courses = new ArrayList<>();

    // logic for register a course
    @Override
    public Course registerCourse(CourseDTO dto) {
        for (Course cou : courses) {
            if (cou.getCode().equalsIgnoreCase(dto.getCode())) {
                throw new IllegalArgumentException("Course is already registered.");
            }
        }
        Course course= new Course(UUID.randomUUID(), dto.getCode(), dto.getTitle(),dto.getInstructor());
        courses.add(course);
        return course;
    }

    // logic for get all courses
    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }


    //logic for get particular course by id
    @Override
    public Course getCourseById(UUID id) {
        for (Course cou : courses) {
            if (cou.getId().equals(id)) {
                return cou;
            }
        }
        throw new ResourceNotFoundException("Course not found.");
    }

    //logic for paginated
    @Override
    public List<Course> getCoursesPaginated(int page, int size) {
        int start = Math.min(page * size, courses.size());
        int end = Math.min(start + size, courses.size());
        return courses.subList(start, end);
    }

}
