package com.example.tinder.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class PofileActivity extends AppCompatActivity implements RelationShipCallBack, InviteRequestCallBack {

    private CoordinatorLayout mainLayout;
    private TextView display;
    private TextView displayName;
    private TextView name;
    private TextView nameName;
    private TextView AboutMe;
    private TextView AboutMe_Display;
    private TextView Age;
    private TextView AgeDisplay;
    private TextView weight;
    private TextView weightDisplay;
    private TextView heigh;
    private TextView heighDisplay;
    private TextView Gender;
    private TextView genderDisplay;
    private Button invite;
    private ImageView image;
    private CardOfPeople value;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainLayout = findViewById(R.id.main_layout);

        setContentView(R.layout.profile);
        //Esto es para la barra de arriba
        this.getSupportActionBar().setDisplayShowHomeEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

        name = findViewById(R.id.person_name);
        display = findViewById(R.id.display);
        displayName = findViewById(R.id.displayName);
        nameName = findViewById(R.id.nameName);
        AboutMe = findViewById(R.id.AboutMe);
        AboutMe_Display = findViewById(R.id.AboutMe_Display);
        Age = findViewById(R.id.Age);
        AgeDisplay = findViewById(R.id.AgeDisplay);
        weight = findViewById(R.id.weight);
        weightDisplay = findViewById(R.id.weightDisplay);
        heigh = findViewById(R.id.heigh);
        heighDisplay = findViewById(R.id.heighDisplay);
        Gender = findViewById(R.id.Gender);
        genderDisplay = findViewById(R.id.genderDisplay);
        invite = findViewById(R.id.invite);

        image = findViewById(R.id.person_photo);

        value = TinderManager.getInstance().getAaaaa();


            displayName.setText(value.getDisplayName());
            if (value.getUser() != null && value.getUser().getFirstName() != null && value.getUser().getLastName() != null) {
                nameName.setText(value.getUser().getFirstName() + " " + value.getUser().getLastName());
            }
            if  (value.getAboutMe() != null) {
                AboutMe_Display.setText(value.getAboutMe());
            }

            if (value.getShowAge()){
                Calendar a = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                String asd = sdf.format(a.getTime());
                String o = asd.substring(0, 3);
                if  (value.getBirthDate() != null) {
                    int j = Integer.parseInt(o) - Integer.parseInt(value.getBirthDate().substring(0, 3));
                    if (Integer.parseInt(value.getBirthDate().substring(5, 6)) < Integer.parseInt(asd.substring(5, 6))) {
                        j--;
                    } else {
                        if (Integer.parseInt(value.getBirthDate().substring(5, 6)) == Integer.parseInt(asd.substring(5, 6)) &&
                                Integer.parseInt(value.getBirthDate().substring(8, 9)) < Integer.parseInt(asd.substring(8, 9))) {
                            j--;
                        }
                    }
                    AgeDisplay.setText(String.valueOf(j));
                }else{
                    Age.setVisibility(View.INVISIBLE);
                    AgeDisplay.setVisibility(View.INVISIBLE);
                }

            }else {
                Age.setVisibility(View.INVISIBLE);
                AgeDisplay.setVisibility(View.INVISIBLE);
            }

            if (value.getHeight()>0){
                if (value.getUnitSystem().equals("METRIC")) {
                    heighDisplay.setText(String.valueOf(value.getHeight()));
                    weightDisplay.setText(String.valueOf(value.getWeight()));
                }else{
                    heighDisplay.setText(String.valueOf(value.getHeight()*3.28));
                    weightDisplay.setText(String.valueOf(value.getWeight()*2.2));
                }
            }else{
                heigh.setVisibility(View.INVISIBLE);
                heighDisplay.setVisibility(View.INVISIBLE);
            }

            if (value.getGender() == null || value.getGender().equals("DO NOT SHOW")){
                genderDisplay.setVisibility(View.INVISIBLE);
                Gender.setVisibility(View.INVISIBLE);
            }else{
                genderDisplay.setText(value.getGender().getType());
            }

            TinderManager.getInstance().getRelationship(this, (int) value.getId());

            if (value.getPicture() != null){
                byte[] decodedString = Base64.decode(value.getPicture(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                image.setImageBitmap(decodedByte);
            }else {
                image.setImageResource(R.drawable.iscle);
            }

            invite.setOnClickListener(v -> invite());




    }

    private void invite(){

        switch (invite.getText().toString()){

            case "Block":

            break;

            case "Tirar la caña" :
                TinderManager.getInstance().profileInvite(this, (long)value.getId());
                break;

        }

    }

    @Override
    public void onRelationShipSuccess(Object profile) {
        invite.setText("Block");
    }

    @Override
    public void onRelationShipFailed(String reason) {
        invite.setText("Tirar la caña");

        //Snackbar.make(mainLayout, "Login failed: " + reason, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onProfileInviteSuccess() {
        Snackbar.make(mainLayout, "¡Ya sabe que te gusta!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onProfileInviteFailed(String reason) {
        Log.d("INVITE:" , "onProfileInviteFailed: " + reason);
        //Snackbar.make(mainLayout, "Invite failed: " + reason, Snackbar.LENGTH_LONG).show();
    }
}
