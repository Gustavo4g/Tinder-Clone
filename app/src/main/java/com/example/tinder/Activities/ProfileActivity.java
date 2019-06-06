package com.example.tinder.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.InviteRequestCallBack;
import com.example.tinder.Interfaces.RelationShipCallBack;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements RelationShipCallBack, InviteRequestCallBack {
    private static final String TAG = "ProfileActivity";

    private CoordinatorLayout mainLayout;
    private TextView displayNameLabel;
    private TextView displayNameTV;
    private TextView aboutMeLabel;
    private TextView aboutMeTV;
    private TextView ageLabel;
    private TextView ageTV;
    private TextView weightLabel;
    private TextView weightTV;
    private TextView heightLabel;
    private TextView heightTV;
    private TextView genderLabel;
    private TextView genderTV;
    private Button inviteButton;
    private ImageView photoIV;
    private CardOfPeople value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = findViewById(R.id.main_layout2);

        setContentView(R.layout.activity_profile);
        //Esto es para la barra de arriba
        this.getSupportActionBar().setDisplayShowHomeEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

        displayNameLabel = findViewById(R.id.display_name_label);
        displayNameTV = findViewById(R.id.display_name_tv);
        aboutMeLabel = findViewById(R.id.about_me_label);
        aboutMeTV = findViewById(R.id.about_me_tv);
        ageLabel = findViewById(R.id.age_label);
        ageTV = findViewById(R.id.age_tv);
        weightLabel = findViewById(R.id.weight_label);
        weightTV = findViewById(R.id.weight_tv);
        heightLabel = findViewById(R.id.height_label);
        heightTV = findViewById(R.id.height_tv);
        genderLabel = findViewById(R.id.gender_label);
        genderTV = findViewById(R.id.gender_tv);
        inviteButton = findViewById(R.id.invite_button);
        photoIV = findViewById(R.id.photo_iv);

        value = TinderManager.getInstance().getAaaaa();

        if (value.getDisplayName() == null || value.getDisplayName().isEmpty()) {
            displayNameTV.setText("No Name");
        } else {
            displayNameTV.setText(value.getDisplayName());
        }
        if (value.getAboutMe() != null) {
            aboutMeTV.setText(value.getAboutMe());
        }

        if (value.getShowAge()) {
            Calendar a = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
            String asd = sdf.format(a.getTime());
            String o = asd.substring(0, 3);
            if (value.getBirthDate() != null) {
                int j = Integer.parseInt(o) - Integer.parseInt(value.getBirthDate().substring(0, 3));
                if (Integer.parseInt(value.getBirthDate().substring(5, 6)) < Integer.parseInt(asd.substring(5, 6))) {
                    j--;
                } else {
                    if (Integer.parseInt(value.getBirthDate().substring(5, 6)) == Integer.parseInt(asd.substring(5, 6)) &&
                            Integer.parseInt(value.getBirthDate().substring(8, 9)) < Integer.parseInt(asd.substring(8, 9))) {
                        j--;
                    }
                }
                ageTV.setText(String.valueOf(j));
            } else {
                ageLabel.setVisibility(View.GONE);
                ageTV.setVisibility(View.GONE);
            }

        } else {
            ageLabel.setVisibility(View.GONE);
            ageTV.setVisibility(View.GONE);
        }

        if (value.getHeight() > 0) {
            if (value.getUnitSystem().equals("METRIC")) {
                heightTV.setText(String.valueOf(value.getHeight()));
                weightTV.setText(String.valueOf(value.getWeight()));
            } else {
                heightTV.setText(String.valueOf(value.getHeight() * 3.28));
                weightTV.setText(String.valueOf(value.getWeight() * 2.2));
            }
        } else {
            heightLabel.setVisibility(View.GONE);
            heightTV.setVisibility(View.GONE);
        }

        if (value.getGender() == null || value.getGender().equals("DO NOT SHOW")) {
            genderTV.setVisibility(View.GONE);
            genderLabel.setVisibility(View.GONE);
        } else {
            genderTV.setText(value.getGender().getType());
        }

        // Esto no sirve para nada xddddddddddddddddddddddddddddddddddddddd
        //TinderManager.getInstance().getRelationship(this, value.getId());

        if (value.getPicture() != null) {
            byte[] decodedString = Base64.decode(value.getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            photoIV.setImageBitmap(decodedByte);
        } else {
            photoIV.setImageResource(R.drawable.iscle);
        }
        inviteButton.setText("Tirar la caña");
        inviteButton.setOnClickListener(v -> invite());


    }

    private void invite() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.like_sound);
        mp.start();

        switch (inviteButton.getText().toString()) {

            case "Block":

                break;

            case "Tirar la caña":
                Log.d("user", value.getUser().getId() + "");
                TinderManager.getInstance().profileInvite(this, value.getUser().getId());
                break;

        }

    }

    @Override
    public void onRelationShipSuccess(Object profile) {
        inviteButton.setText("Block");
    }

    @Override
    public void onRelationShipFailed(String reason) {
        inviteButton.setText("Tirar la caña");
        Log.d(TAG, "onRelationShipFailed:");

        //Snackbar.make(mainLayout, "Login failed: " + reason, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onProfileInviteSuccess() {
        Snackbar.make(findViewById(R.id.main_layout2), "¡Esperemos que también le gustes!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onProfileInviteFailed(String reason) {
        Snackbar.make(findViewById(R.id.main_layout2), "Error: " + reason, Snackbar.LENGTH_LONG).show();
    }
}
