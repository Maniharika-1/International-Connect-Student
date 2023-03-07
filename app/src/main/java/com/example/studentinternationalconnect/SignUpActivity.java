package com.example.studentinternationalconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.studentinternationalconnect.data.model.Student;
import com.example.studentinternationalconnect.data.repository.datasourceImpl.StudentDataSourceImpl;
import com.example.studentinternationalconnect.databinding.ActivitySignupBinding;
import com.example.studentinternationalconnect.domain.repository.StudentRepositoryImpl;
import com.example.studentinternationalconnect.domain.usecase.SignUpUseCase;
import com.example.studentinternationalconnect.presentation.viewmodel.SignUpViewModel;
import com.example.studentinternationalconnect.presentation.viewmodel.SignUpViewModelFactory;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignupBinding mActivitySignupBinding;
    SignUpViewModel mSignUpViewModel;
    SignUpViewModelFactory mSignUpViewModelFactory;
    String mUserName;
    String mEmail;
    String mPassword;
    String mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideActionBar();
        hideNavigationButtons();

        mActivitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignupBinding.getRoot());

        mSignUpViewModelFactory = new SignUpViewModelFactory(getApplication(),
                new SignUpUseCase(new StudentRepositoryImpl(new StudentDataSourceImpl())));

        mSignUpViewModel = new ViewModelProvider(this, mSignUpViewModelFactory).get(SignUpViewModel.class);

        mActivitySignupBinding.setSignUpViewModel(mSignUpViewModel);
        mActivitySignupBinding.setLifecycleOwner(this);

        mActivitySignupBinding.signUpBtn.setOnClickListener(view -> {

            getInputData();
            Student student = new Student(null, null, mEmail, mPassword, mUserName, null, null, null, null, null, null);
            mSignUpViewModel.signUp(student, mConfirmPassword);

        });

        mActivitySignupBinding.loginTV.setOnClickListener(view -> callLoginIntent());

        mSignUpViewModel.mSignUpSuccessful.observe(this, aBoolean -> {

            if (aBoolean) {

                showANoDialog();

            }
        });

    }

    private void showANoDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign Up Successful");
        builder.setMessage("Your ANumber is A" + SignUpViewModel.mRandomNumber +". Please note this number for login and all your future references.");

        builder.setPositiveButton("OK", (dialogInterface, i) -> {

            dialogInterface.dismiss();

            callLoginIntent();

        });

        builder.show();

    }

    private void callLoginIntent() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void getInputData() {

        mUserName = mActivitySignupBinding.userNameET.getText().toString();
        mEmail = mActivitySignupBinding.emailET.getText().toString();
        mPassword = mActivitySignupBinding.passwordET.getText().toString();
        mConfirmPassword = mActivitySignupBinding.repasswordET.getText().toString();

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