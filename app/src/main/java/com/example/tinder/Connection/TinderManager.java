package com.example.tinder.Connection;

import com.example.tinder.Model.UserToken;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TinderManager {
    private final String TAG = "Salle Tinder";
    private final String BASE_URL = "android2.byted.xyz/";

    private TinderService service;
    private UserToken userToken;

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


}
