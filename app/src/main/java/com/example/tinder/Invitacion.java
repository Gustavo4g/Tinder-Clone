package com.example.tinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(invitacionAdapter);
        
        preparePeopleData();
        

    }

    private void preparePeopleData() {
        CardOfPeople people1 = new CardOfPeople("12","2","2","2");
        peopleList.add(people1);
    }
}
