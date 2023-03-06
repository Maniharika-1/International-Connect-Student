package com.example.studentinternationalconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
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

    ActivitySignupBinding activitySignupBinding;
    SignUpViewModel signUpViewModel;
    SignUpViewModelFactory signUpViewModelFactory;
    String userName;
    String email;
    String password;
    String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideActionBar();
        hideNavigationButtons();

        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(activitySignupBinding.getRoot());

        signUpViewModelFactory = new SignUpViewModelFactory(getApplication(),
                new SignUpUseCase(new StudentRepositoryImpl(new StudentDataSourceImpl())));

        signUpViewModel = new ViewModelProvider(this, signUpViewModelFactory).get(SignUpViewModel.class);

        activitySignupBinding.setSignUpViewModel(signUpViewModel);
        activitySignupBinding.setLifecycleOwner(this);

        activitySignupBinding.signUpBtn.setOnClickListener(view -> {

            getInputData();
            Student student = new Student(null, null, email, password, userName, null, null, null, null, null, null);
            signUpViewModel.signUp(student, confirmPassword);

        });

        activitySignupBinding.loginTV.setOnClickListener(view -> callLoginIntent());

        signUpViewModel.signUpSuccessful.observe(this, aBoolean -> {

            if (aBoolean) {

                showANoDialog();

            }
        });

    }

    private void showANoDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign Up Successful");
        builder.setMessage("Your ANumber is A" + SignUpViewModel.randomNumber +". Please note this number for login and all your future references.");

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

        userName = activitySignupBinding.userNameET.getText().toString();
        email = activitySignupBinding.emailET.getText().toString();
        password = activitySignupBinding.passwordET.getText().toString();
        confirmPassword = activitySignupBinding.repasswordET.getText().toString();

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