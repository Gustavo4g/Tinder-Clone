package com.example.tinder.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.tinder.ChatListAdapter;
import com.example.tinder.Connection.TinderManager;
import com.example.tinder.GenericCallback;
import com.example.tinder.Interfaces.DataCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.ChatRow;
import com.example.tinder.Model.Invite;
import com.example.tinder.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {
    private static final String TAG = "ChatListActivity";

    private RecyclerView recyclerView;
    private ChatListAdapter chatListAdapter;

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

        chatListAdapter = new ChatListAdapter(this, null);
        recyclerView.setAdapter(chatListAdapter);

        TinderManager.getInstance().getFriends(new GenericCallback() {
            @Override
            public void onSuccess(Object data) {
                CardOfPeople[] people = (CardOfPeople[]) data;

                ArrayList<ChatRow> rows = new ArrayList<>();

                for (CardOfPeople p : people) {
                    rows.add(new ChatRow(p.getId(), p.getDisplayName(), "Test message, bitch!"));
                }

                chatListAdapter.setDataset(rows);
            }

            @Override
            public void onFailure(Object data) {
                Toast.makeText(ChatListActivity.this, "Error while getting friends!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
