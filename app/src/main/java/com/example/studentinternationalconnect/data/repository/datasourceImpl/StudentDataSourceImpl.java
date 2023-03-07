package com.example.studentinternationalconnect.data.repository.datasourceImpl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.data.repository.datasource.StudentDataSource;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class StudentDataSourceImpl implements StudentDataSource {

    @Override
    public void saveStudentInfo(Student student, DatabaseReference studentNode, OnCompleteListener onCompleteListener) {

        onCompleteListener.onStart();
        String studentKey = studentNode.push().getKey();
        studentNode.child(studentKey).setValue(student).addOnCompleteListener(task -> {

            if (task.isSuccessful())
                onCompleteListener.onSuccess();
            else onCompleteListener.onFailure();

        });
    }


}
