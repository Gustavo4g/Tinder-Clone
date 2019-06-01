package com.example.tinder.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tinder.ChatListAdapter;
import com.example.tinder.Model.ChatRow;
import com.example.tinder.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // Esto es para la barra de arriba
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatListAdapter chatListAdapter = new ChatListAdapter(this, null);
        recyclerView.setAdapter(chatListAdapter);
    }
}
