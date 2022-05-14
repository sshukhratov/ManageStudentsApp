package com.amigoscode.amigoscode.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("Student already exists");
        }

        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)){
            throw new IllegalStateException("Student ID "+id+" does not exist");
        }

        studentRepository.deleteById(id);
    }

    @Transactional // Entity updates the state therefore we dont use JPASQL methods
    public void updateStudent(Long studentId, String name, String email) {
        // Verify if student exists
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with ID "+studentId+" does not exist"
        ));

        // Update student name
        if (!name.isBlank() && !name.isEmpty() && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        // Update student email
        if (!email.isBlank() && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){

            // Verify if email is not taken already
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email your trying to enter is already taken");
            }

            student.setEmail(email);
        }
    }
}
