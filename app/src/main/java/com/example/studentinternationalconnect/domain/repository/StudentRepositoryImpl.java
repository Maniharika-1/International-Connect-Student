package com.example.studentinternationalconnect.domain.repository;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.data.repository.datasource.StudentDataSource;

public class StudentRepositoryImpl implements StudentRepository{

    StudentDataSource studentDataSource;

    public StudentRepositoryImpl(StudentDataSource studentDataSource) {
        this.studentDataSource = studentDataSource;
    }

    @Override
    public void saveStudentInfo(Student student) {
        studentDataSource.saveStudentInfo(student);
    }
}
