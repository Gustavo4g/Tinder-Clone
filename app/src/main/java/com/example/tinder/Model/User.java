package com.example.tinder.Model;


public class User {
    private float id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
    private String langKey;
    private String imageUrl = null;
    private String resetDate = null;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean getActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResetDate() {
        return resetDate;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }
}