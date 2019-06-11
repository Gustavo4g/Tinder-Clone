package com.example.tinder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Activities.ChatRoomActivity;
import com.example.tinder.Model.ChatRow;
import com.example.tinder.R;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<ChatRow> chatList;

    public ChatListAdapter(Context context, ArrayList<ChatRow> chatList) {
        if (chatList == null) chatList = new ArrayList<>();
        this.context = context;
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

        if (row.getPicture() != null) {
            byte[] decodedString = Base64.decode(row.getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewHolder.image.setImageBitmap(decodedByte);
        } else {
            viewHolder.image.setImageResource(R.drawable.iscle);
        }

        viewHolder.mainLayout.setOnClickListener(v -> {
            Intent chatRoomIntent = new Intent(context, ChatRoomActivity.class);
            chatRoomIntent.putExtra("USER_ID", row.getId());
            chatRoomIntent.putExtra("NAME", row.getName());
            chatRoomIntent.putExtra("PICTURE", row.getPicture());
            context.startActivity(chatRoomIntent);
        });
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
            image = view.findViewById(R.id.photo_iv);
            name = view.findViewById(R.id.person_name);
            lastMessage = view.findViewById(R.id.last_message);
        }
    }
}