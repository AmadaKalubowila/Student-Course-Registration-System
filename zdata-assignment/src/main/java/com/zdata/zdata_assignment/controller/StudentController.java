package com.zdata.zdata_assignment.controller;


import com.zdata.zdata_assignment.dto.StudentDTO;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.model.Student;
import com.zdata.zdata_assignment.service.RegistrationService;
import com.zdata.zdata_assignment.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RegistrationService registrationService;

    // Controller for register student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody @Valid StudentDTO dto) {
        Student student = studentService.registerStudent(dto);
        return ResponseEntity.ok(student);
    }

    // get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    //controller for register student for a course
    @PostMapping("/{studentId}/register/{courseId}")
    public ResponseEntity<String> registerStudentToCourse(@PathVariable UUID studentId, @PathVariable UUID courseId) {
        registrationService.registerStudentToCourse(studentId,courseId);
        return ResponseEntity.ok("Student registered to course successfully.");
    }

    // controller for drop a course student registered
    @DeleteMapping("/{studentId}/drop/{courseId}")
    public ResponseEntity<String> dropStudentFromCourse(@PathVariable UUID studentId, @PathVariable UUID courseId) {
        registrationService.dropStudentFromCourse(studentId,courseId);
        return ResponseEntity.ok("Student drop from course success.");
    }

    // controller for get all registered courses by particular student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getAllCoursersOfStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(registrationService.getCourseByStudentId(studentId));
    }

    // controller for get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(studentService.getStudentPaginated(page, size));
    }


}
