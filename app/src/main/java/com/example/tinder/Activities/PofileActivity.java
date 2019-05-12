package com.example.tinder.Activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.RelationShipCallBack;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Login;
import com.example.tinder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PofileActivity extends AppCompatActivity implements RelationShipCallBack {

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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainLayout = findViewById(R.id.main_layout);
        //al crear la activity hay que passarle de esta forma info del usuario que queremos ver

        //Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        //Bundle b = new Bundle();
        //b.putExtra("key", obj); //Your user
        //intent.putExtras(b); //Put your user to your next Intent
        //startActivity(intent);
        //finish();

        setContentView(R.layout.profile);
        setTitle("Profile - Tinder"); //posar el nom que vulguem

        name = findViewById(R.id.name);
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

        image = findViewById(R.id.image);

        Bundle b = getIntent().getExtras();
        CardOfPeople value;

        if(b != null) {
            value = (CardOfPeople) b.getSerializable("key");
            displayName.setText(value.getDisplayName());
            nameName.setText(value.getUser().getFirstName() + " " + value.getUser().getLastName());
            AboutMe_Display.setText(value.getAboutMe());

            if (value.getShowAge()){
                Calendar a = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                String asd = sdf.format(a.getTime());
                String o = asd.substring(0, 3);
                int j = Integer.parseInt(o)- Integer.parseInt(value.getBirthDate().substring(0, 3));
                if (Integer.parseInt(value.getBirthDate().substring(5, 6)) < Integer.parseInt(asd.substring(5, 6))){
                    j--;
                }else{
                    if (Integer.parseInt(value.getBirthDate().substring(5, 6)) == Integer.parseInt(asd.substring(5, 6)) &&
                            Integer.parseInt(value.getBirthDate().substring(8, 9)) < Integer.parseInt(asd.substring(8, 9))){
                        j--;
                    }
                }
                AgeDisplay.setText(String.valueOf(j));

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

            if (value.getGender().getType().equals("DO NOT SHOW")){
                genderDisplay.setVisibility(View.INVISIBLE);
                Gender.setVisibility(View.INVISIBLE);
            }else{
                genderDisplay.setText(value.getGender().getType());
            }

            TinderManager.getInstance().getRelationship(this, (int) value.getId());


        }


    }

    @Override
    public void onRelationShipSuccess(Object profile) {
        invite.setText("Block");
    }

    @Override
    public void onRelationShipFailed(String reason) {
        invite.setText("Tirar la caña");
        
        Snackbar.make(mainLayout, "Login failed: " + reason, Snackbar.LENGTH_LONG).show();
    }
}
