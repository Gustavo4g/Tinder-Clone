package com.example.tinder.Model;

public class ChatRow {
    private final long id;
    private final String name;
    private final String lastMessage;
    private final String picture;

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
