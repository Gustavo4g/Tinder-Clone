package com.example.tinder.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.GenericCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.R;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements GenericCallback {
    private static final String TAG = "SettingsActivity";

    private ImageView profileIV;
    private EditText nameET;
    private EditText descriptionET;
    private TextView birthdayTV;
    private EditText weight;
    private EditText height;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setTitle("Edit profile - Salle Tinder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileIV = findViewById(R.id.photo_iv);
        nameET = findViewById(R.id.name_et);
        descriptionET = findViewById(R.id.description_et);
        birthdayTV = findViewById(R.id.editText3);
        weight = findViewById(R.id.editText4);
        height = findViewById(R.id.editText5);

        saveButton = findViewById(R.id.save_button);

        profileIV.setImageResource(R.drawable.iscle);

        //profileIV.setOnClickListener(v -> openGallery());
        saveButton.setOnClickListener(v -> sendData());
        //birthdayTV.setOnClickListener(v -> setBirth());
        setBirth();
    }

    private void setBirth() {
        Calendar c = Calendar.getInstance();
        if (true) { // No hi ha cap data de naixement
            c.add(Calendar.YEAR, -18);
        }
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            birthdayTV.setText(String.format("%02d/%02d/%02d", dayOfMonth, month + 1, year));
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void sendData() {
        CardOfPeople newProfile = TinderManager.getInstance().getActualUser();
        newProfile.setAboutMe(descriptionET.getText().toString());
        newProfile.setWeight(Float.parseFloat(weight.getText().toString()));
        newProfile.setHeight(Float.parseFloat(height.getText().toString()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        /*

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        newProfile.setPicture(Base64.encodeToString(imageBytes, Base64.DEFAULT));

        */

        if (true) { // Date is set
            String[] birthday = birthdayTV.getText().toString().split("/");
            newProfile.setBirthDate(birthday[2] + "-" + birthday[1] + "-" + birthday[0]);
        }

        TinderManager.getInstance().profilePut(this, newProfile);
    }

    /*
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
        }
    }
    */

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailure(Object data) {
        runOnUiThread(() -> {
            // Create a Snackbar showing the error to the user
            String message = ((String) data);
            Toast.makeText(SettingsActivity.this, "Login failed: " + message + " \uD83D\uDE05", Toast.LENGTH_LONG).show();
            //Snackbar.make(mainLayout, "Login failed: " + message + " \uD83D\uDE05", Snackbar.LENGTH_LONG).show();
            finishAffinity();
        });
    }
}
