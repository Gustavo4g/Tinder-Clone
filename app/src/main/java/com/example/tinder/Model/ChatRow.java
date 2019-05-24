package com.example.tinder.Model;

public class ChatRow {
    public float id;
    public String name;
    public String lastMessage;

    public ChatRow(float id, String name, String lastMessage) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
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
}
