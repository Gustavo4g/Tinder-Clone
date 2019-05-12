package com.example.tinder.Interfaces;

import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Invite;

public interface DataCallback {
    //Register
    void onRegisterSuccess();
    void onRegisterFailed(String Reason);

    //Login
    void onLoginSuccess(Object UserToken);
    void onLoginFailed(String reason);

    //Profile update
    void onProfilePutSuccess();
    void onProfilePutFailed(String reason);

    //Own profile get
    void onOwnProfileGetSuccess(Object profile);
    void onOwnProfileGetFailed(String Reason);

    //profile GET call
    void onProfileGetSuccess(Object profile);
    void onProfileGetFailed(String Reason);

    //Profile Invite POST call
    void onProfileInviteSuccess();
    void onProfileInviteFailed(String reason);

    //Pending Invites GET
    void onPendingInvitesSuccess(Invite[] invites);
    void onPendingInvitesFailed(String reason);

    //PUT invite accept petition
    void onInviteAnswerSuccess();
    void onInviteAnswerFailure(String reason);

    //GET accepted invites Petition
    void onAcceptedInvitesSuccess(Invite[] invites);
    void onAcceptedInvitesFailed(String reason);

    //GET profiles
    void onGetProfilesSuccess(CardOfPeople[] profiles);
    void onGetProfilesFailed(String reason);


}