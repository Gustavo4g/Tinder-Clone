package com.example.tinder.Interfaces;

public interface LoginCallBack {

    void onLoginSuccess(Object UserToken);
    void onLoginFailed(String reason);
}
