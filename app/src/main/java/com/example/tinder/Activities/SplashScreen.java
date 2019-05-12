package com.example.tinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.tinder.R;
import com.example.tinder.Utils.WaitThread;

public class SplashScreen extends AppCompatActivity {
    private CoordinatorLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_scren);
        setTitle("Loading");

        mainLayout = findViewById(R.id.main_layout);

        //Create a thread to wait for three seconds
        (new Handler()).postDelayed(this::goToLogin, 2000);

    }

    private void goToLogin() {
        runOnUiThread(() -> {
            // Create the MainActivity intent
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            // Start MainActivity via the intent
            startActivity(loginActivityIntent);
            // Finish the SplashScreen
            finishAffinity();
        });
    }

}
