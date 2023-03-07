package com.example.studentinternationalconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.studentinternationalconnect.data.repository.datasourceImpl.StudentDataSourceImpl;
import com.example.studentinternationalconnect.databinding.ActivityLoginBinding;
import com.example.studentinternationalconnect.domain.repository.StudentRepositoryImpl;
import com.example.studentinternationalconnect.domain.usecase.LoginUseCase;
import com.example.studentinternationalconnect.presentation.viewmodel.LoginViewModel;
import com.example.studentinternationalconnect.presentation.viewmodel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mActivityLoginBinding;
    LoginViewModel mLoginViewModel;
    LoginViewModelFactory mLoginViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideActionBar();
        hideNavigationButtons();

        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mActivityLoginBinding.getRoot());

        mLoginViewModelFactory = new LoginViewModelFactory(getApplication(), new LoginUseCase(new StudentRepositoryImpl(new StudentDataSourceImpl())));

        mLoginViewModel = new ViewModelProvider(this, mLoginViewModelFactory).get(LoginViewModel.class);

        mActivityLoginBinding.setLoginViewModel(mLoginViewModel);
        mActivityLoginBinding.setLifecycleOwner(this);

        mActivityLoginBinding.loginBtn.setOnClickListener(view -> mLoginViewModel.login(
                mActivityLoginBinding.userNameET.getText().toString(),
                mActivityLoginBinding.passwordET.getText().toString())
        );

        mActivityLoginBinding.signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callSignUpActivity();

            }
        });

        mLoginViewModel.mLoginSuccessful.observe(this, aBoolean -> {

            if (aBoolean)
                callHomeActivity();

        });

    }

    private void callSignUpActivity() {

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    private void callHomeActivity() {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void hideNavigationButtons() {

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    private void hideActionBar() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}