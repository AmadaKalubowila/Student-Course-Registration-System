package com.zdata.zdata_assignment.serviceImpl;

import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.model.Register;
import com.zdata.zdata_assignment.model.Student;
import com.zdata.zdata_assignment.service.CourseService;
import com.zdata.zdata_assignment.service.RegistrationService;
import com.zdata.zdata_assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final List<Register> register = new ArrayList<>();

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    //logic for register student to a course
    @Override
    public void registerStudentToCourse(UUID studentId, UUID courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        for (Register re : register) {
            if (re.getCourseId().equals(course.getId()) && re.getStudentId().equals(student.getId())) {
                throw new IllegalArgumentException("Student is already registered to this course.");
            }
        }

        Register registered = new Register(studentId, courseId, LocalDateTime.now());
        register.add(registered);
    }

    //logic for drop a course student already registered
    @Override
    public void dropStudentFromCourse(UUID studentId,UUID courseId) {

        boolean removed = register.removeIf(
                r -> r.getStudentId().equals(studentId) && r.getCourseId().equals(courseId)
        );
        if (!removed) {
            throw new ResourceNotFoundException("Registration not found.");
        }
    }

    //logic for fetch all registered courses for particular student by id
    @Override
    public List<Course> getCourseByStudentId(UUID studentId) {
        List<Course> courses = new ArrayList<>();

        Student student=studentService.getStudentById(studentId);
        if (student == null) {
            throw new ResourceNotFoundException("Student ID not found.");
        }
        for (Register r : register) {
            if (r.getStudentId().equals(studentId)) {
                courses.add(courseService.getCourseById(r.getCourseId()));
            }
        }
        return courses;
    }

}
