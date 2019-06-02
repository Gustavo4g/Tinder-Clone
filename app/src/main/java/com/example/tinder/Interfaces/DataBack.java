package com.example.tinder.Interfaces;

import com.example.tinder.Model.Invite;

public interface DataBack {
    //Login
    void onLogin2Success(Object id_token);

    void onLogin2Failed(String reason);

}
