package com.example.studentinternationalconnect.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.domain.usecase.SignUpUseCase;
import com.example.studentinternationalconnect.domain.util.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;

public class SignUpViewModel extends AndroidViewModel {

    private DatabaseReference mStudentNode;
    SignUpUseCase mSignUpUseCase;
    public MutableLiveData<Boolean> mSignUpSuccessful;
    public MutableLiveData<String> mErrorMessage;
    final String TAG = "SignUpViewModel";
    public static int mRandomNumber;

    public SignUpViewModel(Application application, SignUpUseCase signUpUseCase) {
        super(application);
        this.mSignUpUseCase = signUpUseCase;

        mStudentNode = FirebaseDatabase.getInstance().getReference().child("Students");
        mSignUpSuccessful = new MutableLiveData<>();
        mErrorMessage = new MutableLiveData<>();
        mSignUpSuccessful.setValue(false);
        mErrorMessage.setValue(null);
    }

    public void signUp(Student student, String confirmPassword) {

        if (student.getmPassword() != null && !student.getmPassword().contentEquals(confirmPassword))
            mErrorMessage.setValue("Password mismatch. Please enter correct password!");
        else if (student.getmUserName().isEmpty() || student.getmEmail().isEmpty()
                || student.getmPassword().isEmpty() || confirmPassword.isEmpty())
            mErrorMessage.setValue("One or more fields are empty!");
        else {

            int min = 00000000;
            int max = 99999999;
            mRandomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

            student.setmANo('A' + String.valueOf(mRandomNumber));

            mSignUpUseCase.execute(student, mStudentNode, new OnCompleteListener() {
                @Override
                public void onSuccess() {

                    mSignUpSuccessful.setValue(true);
                    mErrorMessage.setValue(null);

                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFailure() {

                    mSignUpSuccessful.setValue(false);
                    mErrorMessage.setValue("Failed to sign up!");

                }
            });

        }

    }

}
