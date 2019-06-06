package com.example.tinder.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Model.ChatRow;
import com.example.tinder.Model.Message;
import com.example.tinder.R;

import java.util.ArrayList;

public class messagesAdapter extends RecyclerView.Adapter<messagesAdapter.ViewHolder> {

    Context context;
    ArrayList<Message> messages;
    String name;

    public messagesAdapter(ArrayList<Message> messages, Context context, String friend){

        this.messages = messages != null ? messages : new ArrayList<>();
        this.context = context;
        this.name = friend;
    }


    @NonNull
    @Override
    public messagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 2) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message, viewGroup, false);
            return new messagesAdapter.ViewHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message_friend, viewGroup, false);
            return new messagesAdapter.ViewHolder(itemView);
        }
    }

    private void convertirImagen(String imagenEnString, ImageView imageViewAponerFoto){
        byte[] decodedString = Base64.decode(imagenEnString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageViewAponerFoto.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemViewType(int position) {
        Message m = messages.get(position);
        if (m.getSender().getDisplayName().equals(name)){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull messagesAdapter.ViewHolder viewHolder, int i) {

        Message message = messages.get((getItemCount() - 1) - i);
        viewHolder.texto.setText( "  " + message.getMessage());


    }

    public void setDataset(ArrayList<Message> messageList) {
        this.messages = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView texto;
        final ConstraintLayout mainLayout;

        ViewHolder(View view) {
            super(view);
            mainLayout = view.findViewById(R.id.main_layout);
            texto = view.findViewById(R.id.chat);
        }
    }
}
