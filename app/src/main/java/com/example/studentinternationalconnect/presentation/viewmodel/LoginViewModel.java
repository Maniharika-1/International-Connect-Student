package com.example.studentinternationalconnect.presentation.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.studentinternationalconnect.StudentBaseClass;
import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.usecase.LoginUseCase;
import com.example.studentinternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends AndroidViewModel {

    private DatabaseReference mStudentNode;
    LoginUseCase mLoginUseCase;
    public MutableLiveData<Boolean> mLoginSuccessful;
    public MutableLiveData<String> mErrorMessage;
    final String TAG = "LoginViewModel";
    SharedPreferences mSharedPreferences;

    public LoginViewModel(@NonNull Application application, LoginUseCase loginUseCase) {

        super(application);
        mLoginUseCase = loginUseCase;

        mStudentNode = FirebaseDatabase.getInstance().getReference().child("Students");
        mErrorMessage = new MutableLiveData<>();
        mLoginSuccessful = new MutableLiveData<>();
        mErrorMessage.setValue(null);
        mLoginSuccessful.setValue(false);

        mSharedPreferences = StudentBaseClass.mSharedPreferences;

    }

    public void login(String userName, String password) {

        mLoginUseCase.execute(mStudentNode, new GetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        if (snapshot.exists()) {

                            Student student = snapshot.getValue(Student.class);

                            if (student != null && student.getUserName() != null && student.getUserName().contentEquals(userName) &&
                            student.getPassword() != null && student.getPassword().contentEquals(password)) {

                                mLoginSuccessful.setValue(true);
                                mErrorMessage.setValue(null);
                                mSharedPreferences.edit().putBoolean("LoggedIn", true).apply();

                            } else {

                                mLoginSuccessful.setValue(false);
                                mErrorMessage.setValue("User not found. Please sign up!");
                                mSharedPreferences.edit().putBoolean("LoggedIn", false).apply();

                            }

                        } else {

                            mLoginSuccessful.setValue(false);
                            mErrorMessage.setValue("User not found. Please sign up!");
                            mSharedPreferences.edit().putBoolean("LoggedIn", false).apply();

                        }
                    }
                }

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure() {

                mLoginSuccessful.setValue(false);
                mErrorMessage.setValue("Couldn't sign up!");
                mSharedPreferences.edit().putBoolean("LoggedIn", false).apply();

            }
        });
    }

}
