package com.example.tinder.Model;

public class ChatRow {
    public long id;
    public String name;
    public String lastMessage;
    public String picture;

    public ChatRow(long id, String name, String lastMessage, String picture) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
        this.picture = picture;
    }

    public long getId() {
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
