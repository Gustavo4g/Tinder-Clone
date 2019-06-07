package com.example.tinder.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.DataCallback;
import com.example.tinder.Interfaces.GenericCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SettingsActivity extends AppCompatActivity implements GenericCallback {

    private EditText weight;
    private EditText height;
    private EditText birth;
    private EditText about;
    private Button image;
    private Button send;
    private String picture;
    private String aboutMe;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        weight = findViewById(R.id.Weight);
        height = findViewById(R.id.Height);
        birth = findViewById(R.id.Birth);
        about = findViewById(R.id.description);
        image = findViewById(R.id.ImageButton);
        send = findViewById(R.id.SendButtonProfile);

        image.setOnClickListener(v -> openGallery());
        send.setOnClickListener(v -> sendData());
    }

    private void sendData() {
        CardOfPeople newProfile = new CardOfPeople();
        newProfile.setAboutMe(about.getText().toString());
        newProfile.setWeight(Float.parseFloat(weight.getText().toString()));
        newProfile.setHeight(Float.parseFloat(height.getText().toString()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        newProfile.setPicture(Base64.encodeToString(imageBytes, Base64.DEFAULT));
        newProfile.setBirthDate(birth.getText().toString());

        TinderManager.getInstance().profilePut(this ,newProfile);
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
        }
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailure(Object data) {
        CoordinatorLayout mainLayout = findViewById(R.id.main_layout);
        runOnUiThread(() -> {
            // Create a Snackbar showing the error to the user
            String message = ((String) data);
            Snackbar.make(mainLayout, "Login failed: " + message + " \uD83D\uDE05", Snackbar.LENGTH_LONG).show();
            finishAffinity();
        });
    }
}
