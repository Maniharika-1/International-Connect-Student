package com.example.studentinternationalconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.example.studentinternationalconnect.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding mActivityWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideActionBar();
        hideNavigationButtons();

        mActivityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(mActivityWelcomeBinding.getRoot());

        SharedPreferences sharedPreferences = StudentBaseClass.mSharedPreferences;

        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        Boolean loggedIn = sharedPreferences.getBoolean("LoggedIn", false);

                        if (loggedIn) {

                            callHomeActivity();
                            content.getViewTreeObserver().removeOnPreDrawListener(this);

                        }

                        return true;
                    }
                });

        mActivityWelcomeBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginActivity();
            }
        });

        mActivityWelcomeBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSignUpActivity();
            }
        });
    }

    private void callSignUpActivity() {

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    private void callLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
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