package com.example.tinder.Model;

public class UserToken {
    private String id_token;

    public UserToken(String id_token) {
        this.id_token = id_token;
    }

    public String getToken() {
        return id_token;
    }
}
