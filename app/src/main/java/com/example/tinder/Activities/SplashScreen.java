package com.example.tinder.Activities;

import android.content.Intent;
import android.os.Bundle;
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
        WaitThread wait = new WaitThread();
        wait.run();

        runOnUiThread(() -> {
            // Create the MainActivity intent
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            // Start MainActivity via the intent
            startActivity(mainActivityIntent);
            // Finish the SplashScreen
            finishAffinity();
        });
    }

}
