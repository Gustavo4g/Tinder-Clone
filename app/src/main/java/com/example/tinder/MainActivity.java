package com.example.tinder;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Para mi amor marc para que cuando tenga lo del login entre en esta pantalla


        Intent peticionAmistad = new Intent(this, Invitacion.class);
        startActivity(peticionAmistad);


    }
}
