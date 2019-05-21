package com.example.tinder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Model.ChatRow;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private ArrayList<ChatRow> chatList;

    public ChatListAdapter(ArrayList<ChatRow> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_list_row, viewGroup, false);
        return new ChatListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHolder viewHolder, int i) {
        ChatRow row = chatList.get(i);

        viewHolder.name.setText(row.getName());
        viewHolder.lastMessage.setText(row.getLastMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void setDataset(ArrayList<ChatRow> chatList) {
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView lastMessage;
        final ImageView image;
        final ConstraintLayout mainLayout;

        ViewHolder(View view) {
            super(view);
            mainLayout = view.findViewById(R.id.main_layout);
            image = view.findViewById(R.id.person_photo);
            name = view.findViewById(R.id.person_name);
            lastMessage = view.findViewById(R.id.last_message);
        }
    }
}