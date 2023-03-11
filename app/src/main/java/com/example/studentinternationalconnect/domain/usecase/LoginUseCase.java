package com.example.studentinternationalconnect.domain.usecase;

import com.example.studentinternationalconnect.domain.repository.StudentRepository;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;

public class LoginUseCase {

    StudentRepository mStudentRepository;
    public LoginUseCase(StudentRepository studentRepository) {
        mStudentRepository = studentRepository;
    }

    public void execute(DatabaseReference studentNode, GetDataListener getDataListener) {
        mStudentRepository.getAllStudents(studentNode, getDataListener);
    }
}
