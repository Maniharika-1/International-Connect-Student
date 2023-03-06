package com.example.studentinternationalconnect.domain.repository;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.data.repository.datasource.StudentDataSource;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;

public class StudentRepositoryImpl implements StudentRepository{

    StudentDataSource studentDataSource;

    public StudentRepositoryImpl(StudentDataSource studentDataSource) {
        this.studentDataSource = studentDataSource;
    }

    @Override
    public void saveStudentInfo(Student student, DatabaseReference studentNode, OnCompleteListener onCompleteListener) {
        studentDataSource.saveStudentInfo(student, studentNode, onCompleteListener);
    }

}
