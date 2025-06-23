package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.dto.StudentDTO;
import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Student;
import com.zdata.zdata_assignment.serviceImpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceImplTest {
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        studentServiceImpl = new StudentServiceImpl();
    }

    //method to test register student
    @Test
    void testRegisterStudent_Success() {
        StudentDTO dto = new StudentDTO("Amada Kalubowila", "kalubowilaamada20@gmail.com");
        Student student = studentServiceImpl.registerStudent(dto);

        assertNotNull(student.getId());
        assertEquals("Amada Kalubowila", student.getName());
        assertEquals("kalubowilaamada20@gmail.com", student.getEmail());

    }

    //method to test student try to register using same email
    @Test
    void testRegisterStudent_DuplicateCode_ThrowsException() {
        StudentDTO dto1 = new StudentDTO("Amada Kalubowila", "kalubowilaamada20@gmail.com");
        studentServiceImpl.registerStudent(dto1);

        StudentDTO dto2 = new StudentDTO("Amada Kalubowila", "kalubowilaamada20@gmail.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.registerStudent(dto2);
        });
        assertEquals("Email already in use.", exception.getMessage());

    }

    //method to test fetch all students
    @Test
    void testGetAllStudents() {
        StudentDTO dto1 = new StudentDTO("Amada Kalubowila", "amada@gmail.com");
        StudentDTO dto2 = new StudentDTO("Rasmi Nivarthana", "rasmi@gmail.com");
        studentServiceImpl.registerStudent(dto1);
        studentServiceImpl.registerStudent(dto2);

        List<Student> students = studentServiceImpl.getAllStudents();
        assertEquals(2, students.size());
    }

    //method to test fetch register student
    @Test
    void testGetStudentById_Found() {
        StudentDTO dto = new StudentDTO("Amada Kalubowila", "kalubowilaamada20@gmail.com");
        Student student = studentServiceImpl.registerStudent(dto);

        Student found = studentServiceImpl.getStudentById(student.getId());
        assertEquals(student.getId(), found.getId());

    }

    //method to test fetch non register student
    @Test
    void testGetStudentById_NotFound() {
        UUID randomId = UUID.randomUUID();
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentServiceImpl.getStudentById(randomId);
        });
        assertEquals("Student not found.", exception.getMessage());
    }
}
