package com.example.tinder.Connection;

import android.util.Log;

import com.example.tinder.Interfaces.DataBack;
import com.example.tinder.Interfaces.DataCallback;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Login;
import com.example.tinder.Model.Register;
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

    public void login(DataCallback loginCallBack, Login login) {
        Call<UserToken> call = service.login(login);
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                if (response.isSuccessful()) {
                    loginCallBack.onLoginSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    loginCallBack.onLoginFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                loginCallBack.onLoginFailed(t.getMessage());
            }
        });
    }

    public void register(DataCallback registerCallback, Register register) {
        Call<Void> call = service.register(register);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    registerCallback.onRegisterSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    registerCallback.onRegisterFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerCallback.onRegisterFailed(t.getMessage());
            }
        });
    }

    public void profilePut(DataCallback dataCallback, CardOfPeople profile){
        Call<Void> call = service.profilePut(userToken.getToken(), profile);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataCallback.onRegisterSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onRegisterFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataCallback.onRegisterFailed(t.getMessage());
            }
        });
    }

    public void ownProfileGet(DataCallback dataCallback){
        Call<CardOfPeople> call = service.myProfileGet(userToken.getToken());

        call.enqueue(new Callback<CardOfPeople>() {
            @Override
            public void onResponse(Call<CardOfPeople> call, Response<CardOfPeople> response) {
                if (response.isSuccessful()) {
                    dataCallback.onRegisterSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onRegisterFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople> call, Throwable t) {
                dataCallback.onRegisterFailed(t.getMessage());
            }
        });
    }

    public void profileGet(DataCallback dataCallback, String userId){
        Call<CardOfPeople> call = service.profileGet(userToken.getToken(), userId);

        call.enqueue(new Callback<CardOfPeople>() {
            @Override
            public void onResponse(Call<CardOfPeople> call, Response<CardOfPeople> response) {
                if (response.isSuccessful()) {
                    dataCallback.onRegisterSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onRegisterFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople> call, Throwable t) {
                dataCallback.onRegisterFailed(t.getMessage());
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
