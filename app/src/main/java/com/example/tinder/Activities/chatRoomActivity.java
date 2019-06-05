package com.example.tinder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tinder.Connection.TinderManager;
import com.example.tinder.GenericCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Message;
import com.example.tinder.Model.Recipient;
import com.example.tinder.Model.SendMensaje;
import com.example.tinder.Model.User;
import com.example.tinder.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class chatRoomActivity extends AppCompatActivity implements GenericCallback {
    private RecyclerView recycle;
    private ArrayList<com.example.tinder.Model.Message> messages;
    private float id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        id = (float) getIntent().getExtras().getSerializable("USER_ID");
        messages = new ArrayList<>();
        TinderManager.getInstance().getMessages((long) id, 20, this);


        //Esta funcion es para enviar mensajes
        enviarMensaje("Tu mensaje puto");

        //Y esta otra para actualizar los mensajes
        actualizaMensajes();

        recycle = findViewById(R.id.recyclerView);
    }

    private void actualizaMensajes() {
        messages.clear();
        TinderManager.getInstance().getMessages((long) id, 20, this);
        //TODO : Para marc llamar para que actualize la view
    }

    private void enviarMensaje(String mensaje) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String a = sdf.format(new Date());

        SendMensaje men = new SendMensaje();
        men.setCreatedDate("");
        men.setPicture("");
        men.setRecipient(new Recipient(id));
        men.setPictureContentType("");
        men.setUrl("");
        men.setMessage(mensaje);
        TinderManager.getInstance().postMessages(this, men);


    }


    @Override
    public void onSuccess(Object data) {
        if (data != null) {
            com.example.tinder.Model.Message[] men = (com.example.tinder.Model.Message[]) data;
            messages.addAll(Arrays.asList(men));
            for (Message fer : messages) {
                Log.d("Pepe", fer.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Object data) {

    }
}
