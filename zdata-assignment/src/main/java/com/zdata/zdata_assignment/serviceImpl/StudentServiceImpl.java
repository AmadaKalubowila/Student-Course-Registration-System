package com.zdata.zdata_assignment.serviceImpl;

import com.zdata.zdata_assignment.dto.StudentDTO;
import com.zdata.zdata_assignment.exception.ResourceNotFoundException;
import com.zdata.zdata_assignment.model.Student;
import com.zdata.zdata_assignment.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class StudentServiceImpl implements StudentService {
    private final List<Student> students = new ArrayList<>();

    //logic for student to register
@Override
    public Student registerStudent(StudentDTO dto) {
        for (Student stu : students) {
            if (stu.getEmail().equalsIgnoreCase(dto.getEmail())) {
                throw new IllegalArgumentException("Email already in use.");
            }
        }
        Student student = new Student(UUID.randomUUID(), dto.getName(), dto.getEmail());
        students.add(student);
        return student;
    }

    //logic to fetch all students
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    //logic to get student by id
    @Override
    public Student getStudentById(UUID id) {
        for (Student stu : students) {
            if (stu.getId().equals(id)) {
                return stu;
            }
        }
        throw new ResourceNotFoundException("Student not found.");
    }


    // logic for pagination
    @Override
    public List<Student> getStudentPaginated(int page, int size) {
        int start = Math.min(page * size, students.size());
        int end = Math.min(start + size, students.size());
        return students.subList(start, end);
    }
}
