package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.dto.CourseDTO;
import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.serviceImpl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    private CourseServiceImpl courseServiceImpl;

    @BeforeEach
    void setUp() {
        courseServiceImpl = new CourseServiceImpl();
    }

    //method for test success when course register
    @Test
    void testRegisterCourse_Success() {
        CourseDTO dto = new CourseDTO("CS101", "Intro to CS", "John Doe");
        Course course = courseServiceImpl.registerCourse(dto);

        assertNotNull(course.getId());
        assertEquals("CS101", course.getCode());
        assertEquals("Intro to CS", course.getTitle());
        assertEquals("John Doe", course.getInstructor());
    }

    // method to test course duplication for register
    @Test
    void testRegisterCourse_DuplicateCode_ThrowsException() {
        CourseDTO dto1 = new CourseDTO("CS101", "Intro to CS", "John Doe");
        courseServiceImpl.registerCourse(dto1);

        CourseDTO dto2 = new CourseDTO("cs101", "Different Title", "Jane Smith");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.registerCourse(dto2);
        });
        assertEquals("Course is already registered.", exception.getMessage());
    }

    //method for test get all course
    @Test
    void testGetAllCourses() {
        CourseDTO dto1 = new CourseDTO("CS101", "Intro to CS", "John Doe");
        CourseDTO dto2 = new CourseDTO("CS102", "Data Structures", "Jane Smith");
        courseServiceImpl.registerCourse(dto1);
        courseServiceImpl.registerCourse(dto2);

        List<Course> courses = courseServiceImpl.getAllCourses();
        assertEquals(2, courses.size());
    }

    //method for test course fetching by id (success)
    @Test
    void testGetCourseById_Found() {
        CourseDTO dto = new CourseDTO("CS101", "Intro to CS", "John Doe");
        Course registered = courseServiceImpl.registerCourse(dto);

        Course found = courseServiceImpl.getCourseById(registered.getId());
        assertEquals(registered.getId(), found.getId());
        assertEquals("CS101", found.getCode());
    }

    // method for test course fetching by id (non register id)
    @Test
    void testGetCourseById_NotFound() {
        UUID randomId = UUID.randomUUID();
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            courseServiceImpl.getCourseById(randomId);
        });
        assertEquals("Course not found.", exception.getMessage());
    }
}
