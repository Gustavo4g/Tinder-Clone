package com.example.tinder.Model;

public class Recipient {
    private long id;

    public Recipient(float id) {
        this.id = (long) id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
