package com.example.tinder.Interfaces;

public interface LoginCallBack {

    void onLoginSuccess(String userToken);

    void onLoginFailed(String reason);
}
