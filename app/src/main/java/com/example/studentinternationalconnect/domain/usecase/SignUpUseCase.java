package com.example.studentinternationalconnect.domain.usecase;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.repository.StudentRepository;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;

public class SignUpUseCase {

    StudentRepository mStudentRepository;
    public SignUpUseCase(StudentRepository studentRepository) {
        mStudentRepository = studentRepository;
    }

    public void execute(Student student, DatabaseReference studentNode, OnCompleteListener onCompleteListener) {
        mStudentRepository.saveStudentInfo(student, studentNode, onCompleteListener);
    }
}
