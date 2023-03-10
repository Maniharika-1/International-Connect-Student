package com.example.studentinternationalconnect.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentinternationalconnect.domain.usecase.SignUpUseCase;

public class SignUpViewModelFactory implements ViewModelProvider.Factory{

    private SignUpUseCase mSignUpUseCase;
    private Application mApplication;

    public SignUpViewModelFactory(Application application, SignUpUseCase signUpUseCase) {
        mApplication = application;
        mSignUpUseCase = signUpUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SignUpViewModel.class))
            return (T) new SignUpViewModel(mApplication, mSignUpUseCase);

        return ViewModelProvider.Factory.super.create(modelClass);
    }

}
