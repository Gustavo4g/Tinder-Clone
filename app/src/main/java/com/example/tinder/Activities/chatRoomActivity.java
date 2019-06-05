package com.example.tinder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.GenericCallback;
import com.example.tinder.Model.Message;
import com.example.tinder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class chatRoomActivity extends AppCompatActivity implements GenericCallback {
    private RecyclerView profileRV;
    private ArrayList<com.example.tinder.Model.Message> messages;
    private float id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        id = (float) getIntent().getExtras().getSerializable("USER_ID");
        messages = new ArrayList<>();
        TinderManager.getInstance().getMessages((long)id,this,false);
        TinderManager.getInstance().getMessages(-1,this,true);
        for (Message fer : messages){
            Log.d("Pepe",fer.getMessage());
        }
    }


    @Override
    public void onSuccess(Object data) {
        com.example.tinder.Model.Message[] men = (com.example.tinder.Model.Message[])data;
        messages.addAll(Arrays.asList(men));
        Collections.sort(messages, (o1, o2) -> {
            SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
            try {
                Date o1_date = defaultDateFormat.parse(o1.getCreatedDate());
                Date o2_date = defaultDateFormat.parse(o2.getCreatedDate());
                return o1_date.compareTo(o2_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    @Override
    public void onFailure(Object data) {

    }
}
