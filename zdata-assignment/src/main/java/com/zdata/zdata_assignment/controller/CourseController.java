package com.zdata.zdata_assignment.controller;


import com.zdata.zdata_assignment.dto.CourseDTO;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.service.CourseService;
import com.zdata.zdata_assignment.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private RegistrationService registrationService;

    //controller for register a course
    @PostMapping
    public ResponseEntity<Course> registerCourses(@RequestBody @Valid CourseDTO dto) {
        Course course = courseService.registerCourse(dto);
        return ResponseEntity.ok(course);
    }

    //controller for get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)  {
        return ResponseEntity.ok(courseService.getCoursesPaginated(page,size));
    }

    //controller for get course by its id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

}
