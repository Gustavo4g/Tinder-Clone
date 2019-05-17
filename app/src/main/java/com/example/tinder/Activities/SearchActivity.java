package com.example.tinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.DataBack;
import com.example.tinder.LocationAdapter;
import com.example.tinder.R;
import com.example.tinder.SearchDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements DataBack {
    private static final String TAG = "SearchActivity";

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

        TinderManager.getInstance().searchUsers(this);

        ImageButton settings = getSupportActionBar().getCustomView().findViewById(R.id.action_gear);
        settings.setOnClickListener(v -> {
            Intent peticionAmistad = new Intent(SearchActivity.this, Invitacion.class);
            startActivity(peticionAmistad);
        });

        profileRV = findViewById(R.id.profile_rv);
        profileRV.setHasFixedSize(true);
        profileRV.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        profileRVAdapter = new LocationAdapter(new ArrayList<>());
        profileRV.setAdapter(profileRVAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Filter button pressed!");

            SearchDialog dialog = new SearchDialog(this) {
                @Override
                public void onSearch(HashMap<String, String> parameters) {
                    // Fer la request!
                    Log.d(TAG, "onSearch: " + parameters.toString());
                }
            };

            dialog.show();
        });
    }

    @Override
    public void onLogin2Success(Object id_token) {
        profileRVAdapter.setDataset(new ArrayList<>(Arrays.asList(TinderManager.getInstance().getCardOfPeople())));
    }

    @Override
    public void onLogin2Failed(String reason) {

    }
}
