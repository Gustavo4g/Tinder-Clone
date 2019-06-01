package com.example.tinder.Interfaces;

public interface GetMessageCallback {
    void onGetMessagesSuccess();

    void onGetMessagesFailed(String reason);
}
