package com.example.tinder.Connection;


import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Invite;
import com.example.tinder.Model.Login;
import com.example.tinder.Model.Register;
import com.example.tinder.Model.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TinderService {

    //Register Call
    @POST("api/register")
    Call<Void> register(@Body Register register);

    //Login Call
    @POST("api/authenticate")
    Call<UserToken> login(@Body Login login );

    //Profile update call
    @PUT("api/my-profile")
    Call<Void> profilePut(@Header("Authorization") String userToken, @Body CardOfPeople profile);

    //Own profile GET call
    @GET("api/my-profile")
    Call<CardOfPeople> myProfileGet(@Header("Authorization") String userToken);

    //Profile GET call, requires user ID
    @GET("api/profiles/{id}")
    Call<CardOfPeople> profileGet(@Header("Authorization") String userToken, @Header("id") String userid);

    //Profile Invite POST, requires user ID
    @POST("api/invite/{userId}")
    Call<Void> profileInvite(@Header("Authorization") String userToken, @Header("userId") String userId);

    //Pending invites GET
    @GET("api/pending-invites")
    Call<Invite[]> prendingInvites(@Header("Authorization") String userToken);

    //PUT invite accept petition
    @PUT("api/invite/{id}/state/{state}")
    Call<Void> inviteAnswer( @Header("Authorization") String userToken, @Header("id") long id, @Header("state") boolean state);

    //GET accepted invites petition
    @GET("api/accepted-invites")
    Call<Invite[]> acceptedInvites(@Header("Authorization") String userToken);

    //GET profiles
    @GET("api/profiles")
    Call<CardOfPeople[]> getProfiles(@Header("Authorization") String userToken);

    @GET("api/relationships/{id}")
    Call<CardOfPeople> getRelationship(@Header("Authorization") String userToken);

}
