package com.example.studentinternationalconnect.data.repository.datasourceImpl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.data.repository.datasource.StudentDataSource;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
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

    @Override
    public void getAllStudents(DatabaseReference studentNode, GetDataListener getDataListener) {

        getDataListener.onStart();
        studentNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    getDataListener.onSuccess(snapshot);
                } else {
                    Log.d("StudentDataSourceImpl", "onDataChange: ");
                    getDataListener.onFailure();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("StudentDataSourceImpl", "onCancelled: " + error.getMessage());
                getDataListener.onFailure();
            }
        });

    }


}
