package com.example.tinder.Model;

public class Invite {
    private boolean accepted;
    private String createdDate;
    private long id;
    private CardOfPeople recieved;
    private CardOfPeople sent;

    public Invite(boolean accepted, String createdDate, int id, CardOfPeople recieved, CardOfPeople sent) {
        this.accepted = accepted;
        this.createdDate = createdDate;
        this.id = id;
        this.recieved = recieved;
        this.sent = sent;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardOfPeople getRecieved() {
        return recieved;
    }

    public void setRecieved(CardOfPeople recieved) {
        this.recieved = recieved;
    }

    public CardOfPeople getSent() {
        return sent;
    }

    public void setSent(CardOfPeople sent) {
        this.sent = sent;
    }
}
