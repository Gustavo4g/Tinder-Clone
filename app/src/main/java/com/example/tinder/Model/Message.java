package com.example.tinder.Model;

public class Message {
    private String createdDate;
    private int id;
    private String message;
    private String picture;
    private String pictureContentType;
    private CardOfPeople recipient;
    private CardOfPeople sender;
    private String url;

    public Message(CardOfPeople recipient, CardOfPeople sender) {
        this.recipient = recipient;
        this.sender = sender;
    }

    public Message(String createdDate, int id, String message, String picture, String pictureContentType, CardOfPeople recipient, CardOfPeople sender, String url) {
        this.createdDate = createdDate;
        this.id = id;
        this.message = message;
        this.picture = picture;
        this.pictureContentType = pictureContentType;
        this.recipient = recipient;
        this.sender = sender;
        this.url = url;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public CardOfPeople getRecipient() {
        return recipient;
    }

    public void setRecipient(CardOfPeople recipient) {
        this.recipient = recipient;
    }

    public CardOfPeople getSender() {
        return sender;
    }

    public void setSender(CardOfPeople sender) {
        this.sender = sender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
