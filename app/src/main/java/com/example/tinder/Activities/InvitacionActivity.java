package com.example.tinder.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.example.tinder.Adapters.InvitacionAdapter;
import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvitacionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CardOfPeople> peopleList = null;
    private InvitacionAdapter invitacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitacion);
        peopleList = new ArrayList<>(Arrays.asList(TinderManager.getInstance().getCardOfPeople()));
        this.getSupportActionBar().setDisplayShowHomeEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        invitacionAdapter = new InvitacionAdapter(peopleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(invitacionAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
//        invitacionAdapter.notifyDataSetChanged();

    }


}
