package com.example.studentinternationalconnect.data.repository.datasource;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;

public interface StudentDataSource {

    void saveStudentInfo(Student student, DatabaseReference studentNode, OnCompleteListener onCompleteListener);
    void getAllStudents(DatabaseReference studentNode, GetDataListener getDataListener);

}
