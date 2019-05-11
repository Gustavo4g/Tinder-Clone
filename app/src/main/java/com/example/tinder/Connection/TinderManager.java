package com.example.tinder.Connection;

import android.util.Log;

import com.example.tinder.Interfaces.DataBack;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.UserToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TinderManager {
    private final String TAG = "Salle Tinder";
    private final String BASE_URL = "http://android2.byted.xyz/";

    private TinderService service;
    private UserToken userToken;

    private CardOfPeople[] cardOfPeople;

    private TinderManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(TinderService.class);
    }


    private static class Holder {
        private static final TinderManager instance = new TinderManager();
    }

    public static TinderManager getInstance() {
        return Holder.instance;
    }

    private String getProblem(int responseCode) {
        switch (responseCode) {
            case 400: // Bad request
                return "Bad request";
            case 401: // Unauthorized
                return "Acces not authorized";
            default: // In case we don't know what the error is...
                return "Unknown error";
        }
    }


    public void searchUsers(DataBack mainActivity) {
        Call<CardOfPeople[]> call = service.getProfiles("Bearer " + userToken.getToken());
        call.enqueue(new Callback<CardOfPeople[]>() {
            @Override
            public void onResponse(Call<CardOfPeople[]> call, Response<CardOfPeople[]> response) {
                if (response.isSuccessful()){
                    cardOfPeople = response.body();
                    mainActivity.onLogin2Success(null);
                }else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    mainActivity.onLogin2Failed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople[]> call, Throwable t) {
                mainActivity.onLogin2Failed(t.getMessage());
            }
        });
    }


    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public CardOfPeople[] getCardOfPeople() {
        return cardOfPeople;
    }
}
