package com.example.tinder.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tinder.Model.Message;
import com.example.tinder.R;

import java.util.ArrayList;

public class messagesAdapter extends RecyclerView.Adapter<messagesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Message> messages;
    private String name;

    public messagesAdapter(ArrayList<Message> messages, Context context, String friend) {
        this.messages = messages != null ? messages : new ArrayList<>();
        this.context = context;
        this.name = friend;
    }

    @NonNull
    @Override
    public messagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;

        if (getItemViewType(i) == 1) { // Friend
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message_friend, viewGroup, false);
        } else {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message, viewGroup, false);
        }

        return new messagesAdapter.ViewHolder(itemView);
    }

    private Bitmap convertirImagen(String imagenEnString) {
        byte[] decodedString = Base64.decode(imagenEnString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    @Override
    public int getItemViewType(int position) {
        Message m = messages.get(position);

        if (m.getSender().getDisplayName().equals(name)) { // Friend
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull messagesAdapter.ViewHolder viewHolder, int i) {
        Message message = messages.get((getItemCount() - 1) - i);
        viewHolder.texto.setText(message.getMessage());
        if (!message.getPicture().equals("")) {
            BitmapDrawable drawableLeft = new BitmapDrawable(context.getResources(), convertirImagen(message.getPicture()));
            viewHolder.texto.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            viewHolder.texto.setText("");
        }
    }

    public void setDataset(ArrayList<Message> messageList) {
        this.messages = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView texto;
        final ConstraintLayout mainLayout;

        ViewHolder(View view) {
            super(view);
            mainLayout = view.findViewById(R.id.main_layout);
            texto = view.findViewById(R.id.chat);
        }
    }
}
