package com.example.tinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.LoginCallBack;
import com.example.tinder.Model.Login;
import com.example.tinder.R;

public class LoginActivity extends AppCompatActivity  implements LoginCallBack {
    private CoordinatorLayout mainLayout;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;
    private LinearLayout loadingLayout;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login - Tinder"); //posar el nom que vulguem

        mainLayout = findViewById(R.id.main_layout);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        rememberMeCheckBox = findViewById(R.id.rememberMe);
        loadingLayout = findViewById(R.id.loading_layout);

        Button loginButton = findViewById(R.id.login);
        Button forgotPassword = findViewById(R.id.forgot_password);
        Button registerButton = findViewById(R.id.register);

        loginButton.setOnClickListener(v -> login());
        forgotPassword.setOnClickListener(v -> forgotPassword());
        registerButton.setOnClickListener(v -> register());

    }

    private void login() {
        username = this.usernameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        boolean rememberMe = rememberMeCheckBox.isSelected();

        // TODO: Check for errors in user input!

        // Show the loading layout
        loadingLayout.setVisibility(View.VISIBLE);
        TinderManager.getInstance().login(this, new Login(username, password, rememberMe));
    }

    private void forgotPassword() {
         Snackbar.make(mainLayout, "No, you didn't", Snackbar.LENGTH_LONG).show();
    }

    private void register() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }


    @Override
    public void onLoginSuccess(Object UserToken) {

        runOnUiThread(() -> {
            // Create the MainActivity intent
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            // Start MainActivity via the intent
            startActivity(mainActivityIntent);
            // Finish the LoginActivity
            finishAffinity();
        });
    }

    @Override
    public void onLoginFailed(String reason) {

        runOnUiThread(() -> {
            // Hide the loading layout
            loadingLayout.setVisibility(View.GONE);
            // Create a Snackbar showing the error to the user
            Snackbar.make(mainLayout, "Login failed: " + reason, Snackbar.LENGTH_LONG).show();
        });
    }
}
