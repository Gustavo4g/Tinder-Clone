package com.example.tinder.Model;

public class ChatRow {
    public String name;
    public String lastMessage;

    public ChatRow(String name, String lastMessage) {
        this.name = name;
        this.lastMessage = lastMessage;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
