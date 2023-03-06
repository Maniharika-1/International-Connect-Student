package com.example.studentinternationalconnect.presentation.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.studentinternationalconnect.StudentBaseClass;
import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.usecase.SignUpUseCase;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;

public class SignUpViewModel extends AndroidViewModel {

    private DatabaseReference studentNode;
    SignUpUseCase signUpUseCase;
    public MutableLiveData<Boolean> signUpSuccessful;
    public MutableLiveData<String> errorMessage;
    final String TAG = "SignUpViewModel";
    public static int randomNumber;

    public SignUpViewModel(Application application, SignUpUseCase signUpUseCase) {
        super(application);
        this.signUpUseCase = signUpUseCase;

        studentNode = FirebaseDatabase.getInstance().getReference().child("Students");
        signUpSuccessful = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        signUpSuccessful.setValue(false);
        errorMessage.setValue(null);
    }

    public void signUp(Student student, String confirmPassword) {

        if (student.getPassword() != null && !student.getPassword().contentEquals(confirmPassword))
            errorMessage.setValue("Password mismatch. Please enter correct password!");
        else if (student.getUserName().isEmpty() || student.getEmail().isEmpty()
                || student.getPassword().isEmpty() || confirmPassword.isEmpty())
            errorMessage.setValue("One or more fields are empty!");
        else {

            int min = 00000000;
            int max = 99999999;
            randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

            student.setANo('A' + String.valueOf(randomNumber));

            signUpUseCase.execute(student, studentNode, new OnCompleteListener() {
                @Override
                public void onSuccess() {

                    signUpSuccessful.setValue(true);
                    errorMessage.setValue(null);

                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFailure() {

                    signUpSuccessful.setValue(false);
                    errorMessage.setValue("Failed to sign up!");

                }
            });

        }

    }

}
