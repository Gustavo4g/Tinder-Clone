package com.example.tinder.Activities;

import android.location.Location;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.InvitacionAdapter;
import com.example.tinder.LocationAdapter;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView profileRV;
    private LocationAdapter profileRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Esto es para la barra de arriba
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        profileRV = findViewById(R.id.profile_rv);
        profileRV.setHasFixedSize(true);
        profileRV.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        profileRVAdapter = new LocationAdapter(new ArrayList<>(Arrays.asList(TinderManager.getInstance().getCardOfPeople())));
        profileRV.setAdapter(profileRVAdapter);
    }


}
