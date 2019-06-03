package com.example.tinder.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.IIsAuthenticated;
import com.example.tinder.R;

public class SplashActivity extends AppCompatActivity implements IIsAuthenticated {
    private static final String TAG = "SplashActivity";

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mp = MediaPlayer.create(this, R.raw.intro_sound);
        mp.start();

        setTitle("Loading");

        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        if (sharedPref.getBoolean("rememberMe", false)) {
            String token = sharedPref.getString("userToken", null);
            if (token == null) {
                Log.d(TAG, "onCreate: Null token!");
                goToLogin();
            } else {
                Log.d(TAG, "onCreate: Calling authenticate...");
                TinderManager.getInstance().authenticate(this, token);
            }
        } else {
            Log.d(TAG, "onCreate: No remember me...");
            goToLogin();
        }
    }

    private void goToSearchActivity() {
        runOnUiThread(() -> {
            // Start SearchActivity via the intent
            startActivity(new Intent(this, SearchActivity.class));
            // Finish the SplashActivity
            while (mp.isPlaying());
            finishAffinity();
        });
    }

    private void goToLogin() {
        runOnUiThread(() -> {
            // Create the MainActivity intent
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            // Start MainActivity via the intent
            startActivity(loginActivityIntent);
            // Finish the SplashActivity
            while (mp.isPlaying());
            finishAffinity();
        });
    }

    @Override
    public void success() {
        goToSearchActivity();
    }

    @Override
    public void failure() {
        sharedPrefEditor.putBoolean("rememberMe", false);
        sharedPrefEditor.apply();
        goToLogin();
    }
}
