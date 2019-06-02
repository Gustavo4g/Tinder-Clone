package com.example.tinder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.tinder.Interfaces.InviteRequestCallBack;
import com.example.tinder.Interfaces.PostMessageCallback;
import com.example.tinder.R;

public class chatRoomActivity extends AppCompatActivity implements PostMessageCallback, InviteRequestCallBack {
    private RecyclerView profileRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);

    }

    @Override
    public void onProfileInviteSuccess() {

    }

    @Override
    public void onProfileInviteFailed(String reason) {

    }

    @Override
    public void onPostMessageSuccess() {

    }

    @Override
    public void onPostMessageFailed(String reason) {

    }
}
