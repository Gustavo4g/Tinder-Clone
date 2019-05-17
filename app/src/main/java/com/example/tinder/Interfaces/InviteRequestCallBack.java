package com.example.tinder.Interfaces;

public interface InviteRequestCallBack {
    //Profile Invite POST call
    void onProfileInviteSuccess();
    void onProfileInviteFailed(String reason);
}
