package com.example.tinder.Model;

public class ChatRow {
    public float id;
    public String name;
    public String lastMessage;
    public String picture;

    public ChatRow(float id, String name, String lastMessage, String picture) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
        this.picture = picture;
    }

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getPicture() {
        return picture;
    }
}
