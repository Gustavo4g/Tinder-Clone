package com.example.tinder.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Adapters.ChatListAdapter;
import com.example.tinder.Adapters.messagesAdapter;
import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Interfaces.GenericCallback;
import com.example.tinder.Model.Message;
import com.example.tinder.Model.Recipient;
import com.example.tinder.Model.SendMensaje;
import com.example.tinder.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class chatRoomActivity extends AppCompatActivity implements GenericCallback {
    private RecyclerView recycle;
    private ImageView perfil;
    private TextView name;
    private ImageView altra;
    private Button button;
    private EditText tersto;
    private ArrayList<com.example.tinder.Model.Message> messages;
    private float id;
    private String picture;
    private String nameFriend;
    private messagesAdapter messagesAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_personal_chat);
        id = (float) getIntent().getExtras().getSerializable("USER_ID");
        nameFriend = (String) getIntent().getExtras().getSerializable("NAME");
        picture = (String) getIntent().getExtras().getSerializable("PICTURE");
        messages = new ArrayList<>();



        //Y esta otra para actualizar los mensajes
        //actualizaMensajes();

        recycle = findViewById(R.id.recyclerView);

        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        messagesAdapterView = new messagesAdapter(messages, this, nameFriend);
        recycle.setAdapter(messagesAdapterView);

        perfil = findViewById(R.id.personalImage);
        if (picture != null) {
            byte[] decodedString = Base64.decode(picture, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            perfil.setImageBitmap(decodedByte);
        } else {
            perfil.setImageResource(R.drawable.iscle);
        }


        name = findViewById(R.id.nameChat);
        name.setText(nameFriend);

        tersto = findViewById(R.id.introduccion);
        button = findViewById(R.id.button);
        altra = findViewById(R.id.atra);

        if  (tersto.getText() != null)
            button.setOnClickListener(v -> enviarMensaje(tersto.getText().toString()));

        altra.setOnClickListener(v -> atras());

        TinderManager.getInstance().getMessages((long) id, 20, this);

        recycle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    recycle.smoothScrollToPosition(recycle.getAdapter().getItemCount() - 1);
                }
            }
        });
    }

    private void actualizaMensajes() {
        messages.clear();
        TinderManager.getInstance().getMessages((long) id, 20, this);
        //TODO : Para marc llamar para que actualize la view
    }

    private void atras(){
        finish();
    }

    private void enviarImagen(ImageView imagen){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagen.buildDrawingCache();
        Bitmap bitmap = imagen.getDrawingCache();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String mensaje = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        enviarMensaje(mensaje);
    }

    private void enviarMensaje(String mensaje) {
        SendMensaje men = new SendMensaje();
        men.setCreatedDate("");
        men.setPicture("");
        men.setRecipient(new Recipient(id));
        men.setPictureContentType("");
        men.setUrl("");
        men.setMessage(mensaje);
        TinderManager.getInstance().postMessages(this, men);
        tersto.setText("");

    }


    @Override
    public void onSuccess(Object data) {
        if (data != null) {
            messages.clear();
            com.example.tinder.Model.Message[] men = (com.example.tinder.Model.Message[]) data;
            messages.addAll(Arrays.asList(men));
            for (Message fer : messages) {
                Log.d("Pepe", fer.getMessage());
            }
            messagesAdapterView.setDataset(messages);
            recycle.smoothScrollToPosition(messagesAdapterView.getItemCount() - 1);
        }else{
            actualizaMensajes();
        }
    }

    @Override
    public void onFailure(Object data) {

    }
}
