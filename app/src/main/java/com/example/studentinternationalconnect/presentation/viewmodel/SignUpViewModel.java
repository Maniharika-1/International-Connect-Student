package com.example.studentinternationalconnect.presentation.viewmodel;

import com.example.studentinternationalconnect.domain.usecase.SignUpUseCase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpViewModel {

    private DatabaseReference studentReference;

    SignUpUseCase signUpUseCase;

    public SignUpViewModel(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
        studentReference = FirebaseDatabase.getInstance().getReference().child("Students");
    }

}
