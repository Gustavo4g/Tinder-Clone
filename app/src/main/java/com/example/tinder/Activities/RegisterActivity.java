package com.example.tinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.LoginCallBack;
import com.example.tinder.Interfaces.RegisterCallBack;
import com.example.tinder.Model.Login;
import com.example.tinder.Model.Register;
import com.example.tinder.R;

public class RegisterActivity extends AppCompatActivity implements RegisterCallBack, LoginCallBack {

    private CoordinatorLayout mainLayout;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmationEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Register - Salle Tinder");

        mainLayout = findViewById(R.id.main_layout);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        passwordConfirmationEditText = findViewById(R.id.password_confirmation);
        emailEditText = findViewById(R.id.email);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> register());
    }

    private void register() {
        String username = this.usernameEditText.getText().toString();
        String email = this.emailEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        String passwordConfirmation = this.passwordConfirmationEditText.getText().toString();

        // TODO : Formal verification of password and email
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Snackbar.make(mainLayout, "Hey! Fields can't be empty \uD83D\uDE21", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(mainLayout, "Hmm... That's an invalid email \uD83D\uDE12", Snackbar.LENGTH_LONG).show();
            emailEditText.requestFocus();
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            Snackbar.make(mainLayout, "Oops! Your password don't match \uD83D\uDE31", Snackbar.LENGTH_LONG).show();
            passwordConfirmationEditText.requestFocus();
            return;
        }

        TinderManager.getInstance().register(this, new Register(email, "en", username, password));
    }

    @Override
    public void onRegisterSuccess() {
        runOnUiThread(() -> Snackbar.make(mainLayout, "Registered successfully \uD83D\uDE01", Snackbar.LENGTH_LONG).show());

        String username = this.usernameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        TinderManager.getInstance().login(this, new Login(username, password, false));
    }

    @Override
    public void onRegisterFailed(String reason) {

        runOnUiThread(() -> {
            // Create a Snackbar showing the error to the user
            Snackbar.make(mainLayout, "Register failed: " + reason + " \uD83D\uDE05", Snackbar.LENGTH_LONG).show();
        });
    }

    @Override
    public void onLoginSuccess(String userToken) {
        runOnUiThread(() -> {
            // Create the MainActivity intent
            Intent mainActivityIntent = new Intent(this, SearchActivity.class);
            // Start MainActivity via the intent
            startActivity(mainActivityIntent);
            // Finish the LoginActivity
            finishAffinity();
        });
    }

    @Override
    public void onLoginFailed(String reason) {
        runOnUiThread(() -> {
            // Create a Snackbar showing the error to the user
            Snackbar.make(mainLayout, "Login failed: " + reason + " \uD83D\uDE05", Snackbar.LENGTH_LONG).show();
            finishAffinity();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
