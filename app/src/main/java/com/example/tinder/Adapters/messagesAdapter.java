package com.example.tinder.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message, viewGroup, false);
        return new messagesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull messagesAdapter.ViewHolder viewHolder, int i) {

        Message message = messages.get(i);
        if (messages.get(i).getSender().getDisplayName().equals(name))
            viewHolder.texto.setText(message.getMessage());
        else


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
