package com.example.studentinternationalconnect.domain.usecase;

import com.example.studentinternationalconnect.domain.repository.StudentRepository;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;

public class LoginUseCase {

    StudentRepository studentRepository;
    public LoginUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(DatabaseReference studentNode, GetDataListener getDataListener) {
        studentRepository.getAllStudents(studentNode, getDataListener);
    }
}
