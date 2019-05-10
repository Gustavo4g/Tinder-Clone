package com.example.tinder.Model;

public class Register {
    private String email;
    private String lang;
    private String login;
    private String password;

    public Register(String email, String lang, String login, String password) {
        this.email = email;
        this.lang = lang;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

