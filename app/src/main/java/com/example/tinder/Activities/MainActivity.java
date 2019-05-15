package com.example.tinder.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Connection.TinderService;
import com.example.tinder.Interfaces.DataBack;
import com.example.tinder.Interfaces.DataCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.UserToken;
import com.example.tinder.R;

public class MainActivity extends AppCompatActivity implements DataBack {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esto es para la barra de arriba
        this.getSupportActionBar().setDisplayShowHomeEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);


        //Para mi amor marc para que cuando tenga lo del login entre en esta pantalla
        //Este es mi token para hacer pruebas cuando el login este implementado no se necesitara

       // UserToken userToken = new UserToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2wzMyIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU1NzY4MTY3NH0.Huqb8KEWS66_xMaOWBLkiYJz8dKLcCmurgjGz90wFyRcv7_G4qG4Jh3p9tioYlSr8XTBZAYpabKpoNK4NNv5uw");


        //TinderManager.getInstance().setUserToken(userToken);

        TinderManager.getInstance().searchUsers(this);


    }

    @Override
    public void onLogin2Success(Object cardofpeople) {
        runOnUiThread(() -> {
            Intent peticionAmistad = new Intent(this, Invitacion.class);
            //Intent peticionAmistad = new Intent(this, SearchActivity.class);
            this.startActivity(peticionAmistad);

        });
    }

    @Override
    public void onLogin2Failed(String reason) {
        Log.d("MainActivity", "onLogin2Failed: " + reason);
    }
}
