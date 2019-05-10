package com.example.tinder.Connection;


import com.example.tinder.Model.Login;
import com.example.tinder.Model.Register;
import com.example.tinder.Model.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TinderService {

    @POST("api/register")
    Call<Void> register(@Body Register register);

    @POST("api/authenticate")
    Call<UserToken> login(@Body Login login );

    
}
