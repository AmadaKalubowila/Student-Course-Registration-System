package com.zdata.zdata_assignment.service;

import com.zdata.zdata_assignment.dto.StudentDTO;
import com.zdata.zdata_assignment.model.Course;
import com.zdata.zdata_assignment.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student registerStudent(StudentDTO dto);
    List<Student> getAllStudents();
    Student getStudentById(UUID id);
    List<Student> getStudentPaginated(int page, int size);
}
