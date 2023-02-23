package com.example.studentinternationalconnect.domain.usecase;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.repository.StudentRepository;

public class SignUpUseCase {

    StudentRepository studentRepository;
    public SignUpUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(Student student) {
        studentRepository.saveStudentInfo(student);
    }
}
