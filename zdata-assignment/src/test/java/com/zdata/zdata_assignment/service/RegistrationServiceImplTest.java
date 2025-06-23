package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.model.Student;
import com.zdata.zdata_assignment.serviceImpl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseService courseService;

    private UUID studentId;
    private UUID courseId;
    private Student mockStudent;
    private Course mockCourse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentId = UUID.randomUUID();
        courseId = UUID.randomUUID();

        mockStudent = new Student();
        mockStudent.setId(studentId);

        mockCourse = new Course();
        mockCourse.setId(courseId);

        when(studentService.getStudentById(studentId)).thenReturn(mockStudent);
        when(courseService.getCourseById(courseId)).thenReturn(mockCourse);
    }

    //method for test student register to course
    @Test
    void testRegisterStudentToCourse_Success() {
        registrationService.registerStudentToCourse(studentId, courseId);

        List<Course> courses = registrationService.getCourseByStudentId(studentId);
        assertEquals(1, courses.size());
        assertEquals(courseId, courses.get(0).getId());
    }

    //method to test student register to course where student already registered to particular course
    @Test
    void testRegisterStudentToCourse_AlreadyRegistered_ThrowsException() {
        registrationService.registerStudentToCourse(studentId, courseId);

        // Register again with same studentId and courseId -> should throw IllegalArgumentException
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.registerStudentToCourse(studentId, courseId);
        });
        assertEquals("Student is already registered to this course.", thrown.getMessage());
    }

    //method to test student drop register a course
    @Test
    void testDropStudentFromCourse_Success() {

        registrationService.registerStudentToCourse(studentId, courseId);


        assertDoesNotThrow(() -> registrationService.dropStudentFromCourse(studentId, courseId));


        List<Course> courses = registrationService.getCourseByStudentId(studentId);
        assertTrue(courses.isEmpty());
    }

    //method to test student try to drop not registered course
    @Test
    void testDropStudentFromCourse_NotFound_ThrowsException() {

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            registrationService.dropStudentFromCourse(studentId, courseId);
        });
        assertEquals("Registration not found.", thrown.getMessage());
    }

    //method to test student fetch all registered courses
    @Test
    void testGetCourseByStudentId_StudentNotFound_ThrowsException() {
        UUID unknownStudentId = UUID.randomUUID();
        when(studentService.getStudentById(unknownStudentId)).thenReturn(null);

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            registrationService.getCourseByStudentId(unknownStudentId);
        });
        assertEquals("Student ID not found.", thrown.getMessage());
    }
}
