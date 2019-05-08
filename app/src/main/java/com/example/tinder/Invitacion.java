package com.example.tinder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Invitacion extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CardOfPeople> peopleList = new ArrayList<>();
    private InvitacionAdapter invitacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitacion);

        //Esto es para la barra de arriba

        this.getSupportActionBar().setDisplayShowHomeEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        invitacionAdapter = new InvitacionAdapter(peopleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(invitacionAdapter);
        
        preparePeopleData();
        

    }

    private void preparePeopleData() {
        CardOfPeople people1 = new CardOfPeople("Iscle","2","2","Hoy me he comprado una agenda y me he dado cuenta de que le falta algo importantísimo.\n" +
                "– ¿El qué?\n" +
                "– Tu número de teléfono (guiño guiño!) ");
        peopleList.add(people1);
        CardOfPeople people2 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people2);
        CardOfPeople people3 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people3);
        CardOfPeople people4 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people4);
        CardOfPeople people5 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people5);
        CardOfPeople people6 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people6);
        CardOfPeople people7 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people7);
        CardOfPeople people8 = new CardOfPeople("Gustavo","2","12","2");
        peopleList.add(people8);

        invitacionAdapter.notifyDataSetChanged();

    }
}
