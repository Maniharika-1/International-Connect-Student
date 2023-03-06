package com.example.studentinternationalconnect.domain.usecase;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.repository.StudentRepository;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;

public class SignUpUseCase {

    StudentRepository studentRepository;
    public SignUpUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(Student student, DatabaseReference studentNode, OnCompleteListener onCompleteListener) {
        studentRepository.saveStudentInfo(student, studentNode, onCompleteListener);
    }
}
