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

    private DatabaseReference studentNode;
    LoginUseCase loginUseCase;
    public MutableLiveData<Boolean> loginSuccessful;
    public MutableLiveData<String> errorMessage;
    final String TAG = "LoginViewModel";
    SharedPreferences sharedPreferences;

    public LoginViewModel(@NonNull Application application, LoginUseCase loginUseCase) {

        super(application);
        this.loginUseCase = loginUseCase;

        studentNode = FirebaseDatabase.getInstance().getReference().child("Students");
        errorMessage = new MutableLiveData<>();
        loginSuccessful = new MutableLiveData<>();
        errorMessage.setValue(null);
        loginSuccessful.setValue(false);

        sharedPreferences = StudentBaseClass.sharedPreferences;

    }

    public void login(String userName, String password) {

        loginUseCase.execute(studentNode, new GetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        if (snapshot.exists()) {

                            Student student = snapshot.getValue(Student.class);

                            if (student != null && student.getUserName() != null && student.getUserName().contentEquals(userName) &&
                            student.getPassword() != null && student.getPassword().contentEquals(password)) {

                                loginSuccessful.setValue(true);
                                errorMessage.setValue(null);
                                sharedPreferences.edit().putBoolean("LoggedIn", true).apply();

                            } else {

                                loginSuccessful.setValue(false);
                                errorMessage.setValue("User not found. Please sign up!");
                                sharedPreferences.edit().putBoolean("LoggedIn", false).apply();

                            }

                        } else {

                            loginSuccessful.setValue(false);
                            errorMessage.setValue("User not found. Please sign up!");
                            sharedPreferences.edit().putBoolean("LoggedIn", false).apply();

                        }
                    }
                }

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure() {

                loginSuccessful.setValue(false);
                errorMessage.setValue("Couldn't sign up!");
                sharedPreferences.edit().putBoolean("LoggedIn", false).apply();

            }
        });
    }

}
